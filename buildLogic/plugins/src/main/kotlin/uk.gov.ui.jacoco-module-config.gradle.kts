import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.configurationcache.extensions.capitalized
import uk.gov.ui.Filters
import uk.gov.ui.ext.BaseVariantExt.capitalisedFlavorName
import uk.gov.ui.jacoco.config.JacocoConnectedTestConfig
import uk.gov.ui.jacoco.config.JacocoUnitTestConfig
import uk.gov.ui.jacoco.filetree.fetcher.AsmFileTreeFetcher
import uk.gov.ui.jacoco.filetree.fetcher.FileTreesFetcher
import uk.gov.ui.jacoco.filetree.fetcher.JavaCompileFileTreeFetcher
import uk.gov.ui.jacoco.filetree.fetcher.KotlinCompileFileTreeFetcher
import com.android.build.api.dsl.LibraryExtension as DslLibraryExtension

plugins {
    jacoco
}

val jacocoVersion: String by rootProject.extra

project.tasks.withType<Test> {
    if (this.hasProperty("jacoco")) {
        (this.property("jacoco") as JacocoTaskExtension)?.let {
            it.isIncludeNoLocationClasses = true
            it.excludes = listOf("jdk.internal.*")
        }
    }
}

project.configure<JacocoPluginExtension> {
    this.toolVersion = jacocoVersion
}

project.configure<LibraryExtension> {
    testCoverage.jacocoVersion = jacocoVersion
    buildTypes {
        debug {
            this.enableAndroidTestCoverage = true
            this.enableUnitTestCoverage = true
            this.isTestCoverageEnabled = true
        }
    }
}

project.configure<DslLibraryExtension> {
    testCoverage.jacocoVersion = jacocoVersion
    buildTypes {
        debug {
            this.enableAndroidTestCoverage = true
            this.enableUnitTestCoverage = true
            this.isTestCoverageEnabled = true
        }
    }
}

project.configure<LibraryAndroidComponentsExtension> {
    this.onVariants { debugLibraryVariant ->
        println("MC: Library variant: ${debugLibraryVariant.name}")
    }
}

project.afterEvaluate {
    (this.findProperty("android") as LibraryExtension).let { extension ->

        extension.libraryVariants.forEach { debugLibraryVariant ->
            if (debugLibraryVariant.buildType.name == "debug") {
                val variantName = debugLibraryVariant.name
                val reportPrefix = "${project.buildDir}/reports/jacoco/$variantName"

                generateUnitTestJacoco(
                    project,
                    reportPrefix,
                    debugLibraryVariant.name,
                    debugLibraryVariant.capitalisedFlavorName,
                )

                generateConnectedTestJacoco(
                    project,
                    reportPrefix,
                    debugLibraryVariant.name,
                    debugLibraryVariant.capitalisedFlavorName,
                )
            }
        }
    }
}

fun generateUnitTestJacoco(
    project: Project,
    reportPrefix: String,
    variantName: String,
    capitalisedVariantFlavorName: String,
) {
    val capitalisedVariantName = variantName.capitalized()
    val customUnitJacocoReportTaskName = "jacoco${capitalisedVariantName}UnitTestReport"
    val androidUnitJacocoReportTask = "create${capitalisedVariantName}UnitTestCoverageReport"
    val unitTestTaskName = "test${capitalisedVariantName}UnitTest"

    val unitReportPrefix = "$reportPrefix/unit"

    val classDirectoriesFetcher = FileTreesFetcher(
        project,
        KotlinCompileFileTreeFetcher(
            project,
            variantName,
            capitalisedVariantFlavorName,
        ),
        JavaCompileFileTreeFetcher(
            project,
            variantName,
            capitalisedVariantFlavorName,
        ),
        AsmFileTreeFetcher(
            project,
            variantName,
            capitalisedVariantFlavorName,
        ),
    )

    JacocoUnitTestConfig(
        project,
        classDirectoriesFetcher,
        capitalisedVariantName,
        unitTestTaskName,
    ).generateCustomJacocoReport(
        excludes = Filters.androidUnitTests,
        dependencies = listOf(unitTestTaskName, androidUnitJacocoReportTask),
        description = "Create coverage report from the '$capitalisedVariantName' unit tests.",
        jacocoTestName = customUnitJacocoReportTaskName,
        outputDir = unitReportPrefix,
    )
}

@Suppress("DEPRECATION") // Having to use LibraryExtension, which exposes BaseVariant.
fun generateConnectedTestJacoco(
    project: Project,
    reportPrefix: String,
    variantName: String,
    capitalisedVariantFlavorName: String,
) {
    val capitalisedVariantName = variantName.capitalized()
    val customConnectedJacocoReportTaskName = "connected${capitalisedVariantName}JacocoTestReport"
    val androidConnectedJacocoReportTask =
        "create${capitalisedVariantName}AndroidTestCoverageReport"
    val connectedTestTask = "connected${capitalisedVariantName}AndroidTest"
    val connectedReportPrefix = "$reportPrefix/connected"

    val classDirectoriesFetcher = FileTreesFetcher(
        project,
        KotlinCompileFileTreeFetcher(
            project,
            variantName,
            capitalisedVariantFlavorName,
        ),
        AsmFileTreeFetcher(
            project,
            variantName,
            capitalisedVariantFlavorName,
        ),
        JavaCompileFileTreeFetcher(
            project,
            variantName,
            capitalisedVariantFlavorName,
        ),
    )

    val customJacocoTaskDescription =
        "Create coverage report from the '$capitalisedVariantName' instrumentation tests."
    JacocoConnectedTestConfig(
        project,
        classDirectoriesFetcher,
        capitalisedVariantName,
        connectedTestTask,
    ).generateCustomJacocoReport(
        excludes = Filters.androidInstrumentationTests,
        dependencies = listOf(connectedTestTask, androidConnectedJacocoReportTask),
        description = customJacocoTaskDescription,
        jacocoTestName = customConnectedJacocoReportTaskName,
        outputDir = connectedReportPrefix,
    )
}
