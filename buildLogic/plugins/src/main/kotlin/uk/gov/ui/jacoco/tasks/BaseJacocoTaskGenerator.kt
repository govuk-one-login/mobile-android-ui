package uk.gov.ui.jacoco.tasks

import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.Filters
import uk.gov.ui.jacoco.config.JacocoCustomConfig

/**
 * Partial implementation of [JacocoTaskGenerator] that primarily acts as a property bag for
 * common properties used within both unit and instrumentation [JacocoCustomConfig] objects.
 *
 * @param project The Gradle [Project] that houses the generated Jacoco task. Used to provide a
 * default value for [reportsDirectoryPrefix].
 * @param variant The name of the build variant. Used as a parameter to obtain relevant Gradle
 * tasks.
 * @param reportsDirectoryPrefix The absolute path of the reports directory for generated Jacoco
 * tasks.
 */
abstract class BaseJacocoTaskGenerator(
    project: Project,
    protected val variant: String,
    protected val reportsDirectoryPrefix: String = "${project.buildDir}/reports/jacoco",
) : JacocoTaskGenerator {

    /**
     * TitleCase representation of the Android app or library's build variant.
     */
    protected val capitalisedVariantName: String = variant.capitalized()

    /**
     * The name of the Android System Development Kit (SDK)'s implementation of Jacoco test
     * reporting. The custom Jacoco reporting tasks aim to replace it's direct usage.
     */
    protected abstract val androidCoverageTaskName: String

    /**
     * The description used for the generated Jacoco report task.
     */
    protected abstract val description: String

    /**
     * The absolute path of the directory that holds generated Jacoco reports. Overwritten values
     * should use [reportsDirectoryPrefix] at the start of this [String].
     */
    protected abstract val reportsBaseDirectory: String

    /**
     * The name of the generated Jacoco report task.
     */
    abstract val testTaskName: String

    /**
     * Create a [JacocoReport] task, using the [configuration] as the mechanism for doing so.
     */
    override fun customTask(): TaskProvider<JacocoReport> = configuration
        .generateCustomJacocoReport(
            excludes = Filters.androidUnitTests,
            dependencies = listOf(testTaskName, androidCoverageTaskName),
            description = description,
            name = name,
            testTaskName = testTaskName,
            reportOutputDir = reportsBaseDirectory,
        )
}
