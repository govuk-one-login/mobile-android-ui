package uk.gov.ui.jacoco.tasks

import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.Filters
import uk.gov.ui.filetree.fetcher.FileTreeFetcher
import uk.gov.ui.jacoco.config.JacocoCombinedTestConfig

class JacocoCombinedTestTaskGenerator(
    private val project: Project,
    private val classDirectoriesFetcher: FileTreeFetcher,
    private val variantName: String,
    private val reportPrefix: String = "${project.buildDir}/reports/jacoco",
    private val configs: Iterable<JacocoTaskGenerator>,
) : JacocoTaskGenerator {

    private val capitalisedVariantName = variantName.capitalized()

    override val config = JacocoCombinedTestConfig(
        project,
        classDirectoriesFetcher,
        configs.map { it.config },
    )

    override val taskName: String = "jacoco${capitalisedVariantName}CombinedTestReport"

    override fun customTask(): TaskProvider<JacocoReport> =
        config.generateCustomJacocoReport(
            excludes = Filters.androidUnitTests,
            dependencies = configs.map { it.taskName },
            description = "Create coverage report from the '$capitalisedVariantName' test reports.",
            taskName = taskName,
            outputDir = "$reportPrefix/combined",
        )
}
