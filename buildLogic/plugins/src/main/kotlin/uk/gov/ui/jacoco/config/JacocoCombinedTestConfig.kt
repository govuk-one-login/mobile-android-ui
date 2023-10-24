package uk.gov.ui.jacoco.config

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.filetree.fetcher.FileTreeFetcher

/**
 * A [JacocoCustomConfig] implementation specifically for aggregating other configurations.
 *
 * Creates a [JacocoReport] task that in turn generates reports from provided sources.
 *
 * @param project The Gradle project that contains the required test task. Also generates the
 * return [FileTree] within the [JacocoCustomConfig] abstract class.
 * @param classDirectoriesFetcher The [FileTreeFetcher] that provides the class directories used for
 * reporting code coverage through Jacoco. Combine for relevant [configurations] before passing in.
 * @param configurations The [JacocoCustomConfig] instances to aggregate.
 */
class JacocoCombinedTestConfig(
    project: Project,
    classDirectoriesFetcher: FileTreeFetcher,
    private val configurations: Iterable<JacocoCustomConfig>,
) : JacocoCustomConfig(
    project,
    classDirectoriesFetcher,
) {

    constructor(
        project: Project,
        classDirectoriesFetcher: FileTreeFetcher,
        vararg config: JacocoCustomConfig,
    ) : this(
        project,
        classDirectoriesFetcher,
        config.toList(),
    )

    override fun getExecutionData(): FileTree = configurations.map {
        it.getExecutionData()
    }.reduce { leftTree, rightTree ->
        leftTree.plus(rightTree)
    }
}
