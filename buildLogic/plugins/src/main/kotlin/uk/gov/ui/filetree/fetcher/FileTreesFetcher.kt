package uk.gov.ui.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import uk.gov.ui.ext.ProjectExt.debugLog

/**
 * Decorator class for containing multiple [FileTreeFetcher] objects.
 *
 * Commonly used for obtaining the class directories, due to having multiple sources.
 *
 * @param project The Gradle [Project] for the [fetchers]. Used to wrap [fetchers] output into
 * [Provider] containers.
 * @param fetchers The sub-[FileTreeFetcher] objects that combine together via unions.
 */
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
                project.debugLog(
                    "${this::class.java.simpleName}: Generated file tree: " +
                        it.get().files,
                )
            }
}
