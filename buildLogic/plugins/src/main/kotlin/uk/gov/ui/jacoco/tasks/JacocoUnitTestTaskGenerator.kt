package uk.gov.ui.jacoco.tasks

import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized
import uk.gov.ui.filetree.fetcher.FileTreeFetcher
import uk.gov.ui.jacoco.config.JacocoCustomConfig
import uk.gov.ui.jacoco.config.JacocoUnitTestConfig

class JacocoUnitTestTaskGenerator(
    project: Project,
    private val classDirectoriesFetcher: FileTreeFetcher,
    variantName: String,
) : BaseJacocoTaskGenerator(
    project,
    variantName,
) {

    override val androidCoverageTaskName: String =
        "create${capitalisedVariantName}UnitTestCoverageReport"
    override val taskName: String get() = "jacoco${capitalisedVariantName}UnitTestReport"
    override val description = "Create coverage report from the '$capitalisedVariantName' unit tests."
    override val reportsBaseDirectory: String get() = "$reportPrefix/unit"
    override val testTaskName: String get() = "test${capitalisedVariantName}UnitTest"

    override val config: JacocoCustomConfig get() = JacocoUnitTestConfig(
        project,
        classDirectoriesFetcher,
        variantName.capitalized(),
    )
}
