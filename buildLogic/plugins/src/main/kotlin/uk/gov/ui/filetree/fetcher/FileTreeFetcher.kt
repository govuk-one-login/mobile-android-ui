package uk.gov.ui.filetree.fetcher

import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider

/**
 * Abstraction for obtaining a [FileTree].
 *
 * Commonly used for obtaining files defined as Gradle task output.
 */
fun interface FileTreeFetcher {

    /**
     * Obtain a reference to a [FileTree] based on the implementation.
     *
     * @param excludes The list of files or regular expression patterns to remove from the
     * internally generated [FileTree].
     *
     * @return A [Provider] object that allows the developer to defer computation until required.
     */
    fun getProvider(excludes: List<String>): Provider<FileTree>
}
