package uk.gov.ui.jacoco.config

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import uk.gov.ui.filetree.fetcher.FileTreeFetcher

class JacocoCombinedTestConfig(
    project: Project,
    classDirectoriesFetcher: FileTreeFetcher,
    private val configs: Iterable<JacocoCustomConfig>,
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

    override fun getExecutionData(): FileTree = configs.map {
        it.getExecutionData()
    }.reduce { leftTree, rightTree ->
        leftTree.plus(rightTree)
    }
}
