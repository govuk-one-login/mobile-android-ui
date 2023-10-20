package uk.gov.ui.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider

data class FileTreesFetcher constructor(
    private val project: Project,
    private val fetchers: Iterable<FileTreeFetcher>,
) : FileTreeFetcher {

    constructor(
        project: Project,
        vararg fetcher: FileTreeFetcher,
    ) : this(project, fetcher.toList())

    override fun getProvider(
        excludes: List<String>,
    ): Provider<FileTree> =
        fetchers
            .map { it.getProvider(excludes) }
            .reduce { leftTree, rightTree ->
                project.provider { leftTree.get() + rightTree.get() }
            }.also {
                println(
                    "${project.name}: ${this::class.java.simpleName}: Generated file tree: " +
                        it.get().files,
                )
            }
}
