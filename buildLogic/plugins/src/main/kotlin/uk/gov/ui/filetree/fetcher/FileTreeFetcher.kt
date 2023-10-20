package uk.gov.ui.filetree.fetcher

import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider

fun interface FileTreeFetcher {
    fun getProvider(excludes: List<String>): Provider<FileTree>
}
