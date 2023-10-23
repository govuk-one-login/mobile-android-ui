package uk.gov.ui.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import uk.gov.ui.ext.ProjectExt.debugLog

/**
 * [FileTreeFetcher] implementation designed to obtain the output files from compiling kotlin files
 * into java files.
 *
 * @param project The Gradle [Project] to base the [getBaseFileTree] output from.
 * @param variant The name of the build variant. Used as a parameter to obtain relevant Gradle
 * tasks.
 * @param capitalisedVariantFlavorName The TitleCase representation of the Android app or library's
 * product flavor. Used as a parameter to obtain relevant Gradle tasks.
 */
class KotlinCompileFileTreeFetcher(
    project: Project,
    variant: String,
    capitalisedVariantFlavorName: String,
) : BaseFileTreeFetcher(
    project,
    variant,
    capitalisedVariantFlavorName,
) {

    override fun getBaseFileTree(): Provider<FileTree> {
        return project.provider {
            getKotlinCompileFileTree(
                "compile${capitalisedVariantName}Kotlin",
            ) ?: getKotlinCompileFileTree(
                "compile${capitalisedVariantFlavorName}Kotlin",
            ) ?: project.fileTree(
                "${project.buildDir}/tmp/kotlin-classes/$variant",
            )
        }.also {
            project.debugLog(
                "KotlinCompileFileTreeFetcher: ${it.get().files}",
            )
        }
    }

    private fun getKotlinCompileFileTree(
        name: String,
    ): FileTree? = performOnFoundTask<KotlinCompile, FileTree>(name) {
        it.destinationDirectory.asFileTree
    }
}
