package uk.gov.ui.jacoco.tasks

import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.Filters
import uk.gov.ui.filetree.fetcher.FileTreeFetcher
import uk.gov.ui.jacoco.config.JacocoCombinedTestConfig
import uk.gov.ui.jacoco.config.JacocoCustomConfig

/**
 * A [JacocoTaskGenerator] implementation for combining the provided [JacocoTaskGenerator]
 * [configurations].
 *
 * @param project The Gradle [Project] that houses the generated Jacoco task. Used to generate the
 * relevant [JacocoCustomConfig] instance and the default value for [reportDirectoryPrefix].
 * @param classDirectoriesFetcher The [FileTreeFetcher] that provides the class directories used for
 * reporting code coverage through Jacoco.
 * @param variant The name of the build variant. Used as a parameter to obtain relevant Gradle
 * tasks.
 * @param reportDirectoryPrefix The absolute path that acts as a starting location for the
 * [project]'s custom Jacoco reports.
 * @param configurations The [JacocoTaskGenerator] instances to aggregate. Used to access the
 * relevant [JacocoCustomConfig] tasks that in turn get generated.
 */
class JacocoCombinedTestTaskGenerator(
    private val project: Project,
    private val classDirectoriesFetcher: FileTreeFetcher,
    variant: String,
    private val reportDirectoryPrefix: String = "${project.buildDir}/reports/jacoco",
    private val configurations: Iterable<JacocoTaskGenerator>,
) : JacocoTaskGenerator {

    private val capitalisedVariantName = variant.capitalized()

    override val configuration = JacocoCombinedTestConfig(
        project,
        classDirectoriesFetcher,
        configurations.map { it.configuration },
    )

    override val name: String = "jacoco${capitalisedVariantName}CombinedTestReport"

    override fun customTask(): TaskProvider<JacocoReport> =
        configuration.generateCustomJacocoReport(
            excludes = Filters.androidUnitTests,
            dependencies = configurations.map { it.name },
            description = "Create coverage report from the '$capitalisedVariantName' test reports.",
            name = name,
            reportOutputDir = "$reportDirectoryPrefix/combined",
        )
}
