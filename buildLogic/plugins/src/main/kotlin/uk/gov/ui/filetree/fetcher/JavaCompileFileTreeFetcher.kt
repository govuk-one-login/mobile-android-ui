package uk.gov.ui.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.compile.JavaCompile
import uk.gov.ui.ext.ProjectExt.debugLog

/**
 * [FileTreeFetcher] implementation designed to obtain the output files from compiling intermediary
 * java files into class files.
 *
 * @param project The Gradle [Project] to base the [getBaseFileTree] output from.
 * @param variant The name of the build variant. Used as a parameter to obtain relevant Gradle
 * tasks.
 * @param capitalisedVariantFlavorName The TitleCase representation of the Android app or library's
 * product flavor. Used as a parameter to obtain relevant Gradle tasks.
 */
class JavaCompileFileTreeFetcher(
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
            getJavaCompileFileTree(
                "compile${capitalisedVariantName}JavaWithJavac",
            ) ?: getJavaCompileFileTree(
                "compile${capitalisedVariantFlavorName}JavaWithJavac",
            ) ?: project.fileTree(
                "${project.buildDir}/intermediates/javac/$variant/classes",
            )
        }.also {
            project.debugLog(
                "JavaCompileFileTreeFetcher: ${it.get().files}",
            )
        }
    }

    private fun getJavaCompileFileTree(
        name: String,
    ): FileTree? = performOnFoundTask<JavaCompile, FileTree>(name) {
        it.destinationDirectory.asFileTree
    }
}
