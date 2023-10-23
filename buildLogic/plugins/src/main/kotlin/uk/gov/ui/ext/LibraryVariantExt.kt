package uk.gov.ui.ext

import com.android.build.gradle.api.LibraryVariant
import org.gradle.api.Project
import uk.gov.ui.ext.BaseVariantExt.capitalisedFlavorName
import uk.gov.ui.filetree.fetcher.AsmFileTreeFetcher
import uk.gov.ui.filetree.fetcher.FileTreesFetcher
import uk.gov.ui.filetree.fetcher.JavaCompileFileTreeFetcher
import uk.gov.ui.filetree.fetcher.KotlinCompileFileTreeFetcher
import uk.gov.ui.jacoco.tasks.JacocoCombinedTestTaskGenerator
import uk.gov.ui.jacoco.tasks.JacocoConnectedTestTaskGenerator
import uk.gov.ui.jacoco.tasks.JacocoTaskGenerator
import uk.gov.ui.jacoco.tasks.JacocoUnitTestTaskGenerator

fun LibraryVariant.generateDebugJacocoTasks(
    project: Project
) {
    if (buildType.name == "debug") {
        val classDirectoriesFetcher = FileTreesFetcher(
            project,
            KotlinCompileFileTreeFetcher(
                project,
                name,
                capitalisedFlavorName,
            ),
            AsmFileTreeFetcher(
                project,
                name,
                capitalisedFlavorName,
            ),
            JavaCompileFileTreeFetcher(
                project,
                name,
                capitalisedFlavorName,
            ),
        )

        val unitTestReportGenerator = JacocoUnitTestTaskGenerator(
            project,
            classDirectoriesFetcher,
            name,
        )

        val connectedTestReportGenerator = JacocoConnectedTestTaskGenerator(
            project,
            classDirectoriesFetcher,
            name,
        )

        val combinedTestReportGenerator = JacocoCombinedTestTaskGenerator(
            project = project,
            classDirectoriesFetcher = classDirectoriesFetcher,
            variantName = name,
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