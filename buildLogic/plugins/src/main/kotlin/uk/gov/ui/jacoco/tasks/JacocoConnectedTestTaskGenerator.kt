package uk.gov.ui.jacoco.tasks

import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import uk.gov.ui.filetree.fetcher.FileTreeFetcher
import uk.gov.ui.jacoco.config.JacocoConnectedTestConfig
import uk.gov.ui.jacoco.config.JacocoCustomConfig

class JacocoConnectedTestTaskGenerator(
    project: Project,
    private val classDirectoriesFetcher: FileTreeFetcher,
    variantName: String,
) : BaseJacocoTaskGenerator(
    project,
    variantName,
) {

    override val androidCoverageTaskName: String =
        "create${capitalisedVariantName}AndroidTestCoverageReport"
    override val taskName: String =
        "jacoco${capitalisedVariantName}ConnectedTestReport"
    override val description =
        "Create coverage report from the '$capitalisedVariantName' instrumentation tests."
    override val reportsBaseDirectory: String get() = "$reportPrefix/connected"
    override val testTaskName: String get() = "connected${capitalisedVariantName}AndroidTest"

    override val config: JacocoCustomConfig get() = JacocoConnectedTestConfig(
        project,
        classDirectoriesFetcher,
        variantName.capitalized(),
    )
}
