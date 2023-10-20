import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import uk.gov.ui.ext.BaseVariantExt.capitalisedFlavorName
import uk.gov.ui.filetree.fetcher.AsmFileTreeFetcher
import uk.gov.ui.filetree.fetcher.FileTreesFetcher
import uk.gov.ui.filetree.fetcher.JavaCompileFileTreeFetcher
import uk.gov.ui.filetree.fetcher.KotlinCompileFileTreeFetcher
import uk.gov.ui.jacoco.tasks.JacocoCombinedTestTaskGenerator
import uk.gov.ui.jacoco.tasks.JacocoConnectedTestTaskGenerator
import uk.gov.ui.jacoco.tasks.JacocoTaskGenerator
import uk.gov.ui.jacoco.tasks.JacocoUnitTestTaskGenerator
import com.android.build.api.dsl.LibraryExtension as DslLibraryExtension

plugins {
    jacoco
}

val jacocoVersion: String by rootProject.extra

project.tasks.withType<Test> {
    if (this.hasProperty("jacoco")) {
        (this.property("jacoco") as JacocoTaskExtension).let {
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

                val classDirectoriesFetcher = FileTreesFetcher(
                    project,
                    KotlinCompileFileTreeFetcher(
                        project,
                        variantName,
                        debugLibraryVariant.capitalisedFlavorName,
                    ),
                    AsmFileTreeFetcher(
                        project,
                        variantName,
                        debugLibraryVariant.capitalisedFlavorName,
                    ),
                    JavaCompileFileTreeFetcher(
                        project,
                        variantName,
                        debugLibraryVariant.capitalisedFlavorName,
                    ),
                )

                val unitTestReportGenerator = JacocoUnitTestTaskGenerator(
                    project,
                    classDirectoriesFetcher,
                    variantName,
                )

                val connectedTestReportGenerator = JacocoConnectedTestTaskGenerator(
                    project,
                    classDirectoriesFetcher,
                    variantName,
                )

                val combinedTestReportGenerator = JacocoCombinedTestTaskGenerator(
                    project = project,
                    classDirectoriesFetcher = classDirectoriesFetcher,
                    variantName = variantName,
                    configs = listOf(
                        unitTestReportGenerator,
                        connectedTestReportGenerator,
                    ),
                )

                listOf(
                    unitTestReportGenerator,
                    connectedTestReportGenerator,
                    combinedTestReportGenerator,
                ).forEach(JacocoTaskGenerator::customTask)
            }
        }
    }
}
