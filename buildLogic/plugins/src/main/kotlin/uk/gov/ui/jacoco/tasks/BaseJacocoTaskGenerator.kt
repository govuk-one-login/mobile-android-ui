package uk.gov.ui.jacoco.tasks

import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.Filters

abstract class BaseJacocoTaskGenerator(
    protected val project: Project,
    protected val variantName: String,
    protected val reportPrefix: String = "${project.buildDir}/reports/jacoco",
) : JacocoTaskGenerator {
    protected val capitalisedVariantName: String = variantName.capitalized()
    protected abstract val androidCoverageTaskName: String
    protected abstract val description: String
    protected abstract val reportsBaseDirectory: String

    abstract val testTaskName: String

    override fun customTask(): TaskProvider<JacocoReport> = config.generateCustomJacocoReport(
        excludes = Filters.androidUnitTests,
        dependencies = listOf(testTaskName, androidCoverageTaskName),
        description = description,
        taskName = taskName,
        testTaskName = testTaskName,
        outputDir = reportsBaseDirectory,
    )
}
