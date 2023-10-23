package uk.gov.ui.filetree.fetcher

import com.android.build.gradle.tasks.TransformClassesWithAsmTask
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import uk.gov.ui.ext.ProjectExt.debugLog

/**
 * [FileTreeFetcher] implementation designed to obtain the output files from the
 * [ASM bytecode framework](https://asm.ow2.io/).
 *
 * Used by android app Gradle modules as part of pro-guard obfuscation.
 *
 * @param project The Gradle [Project] to base the [getBaseFileTree] output from.
 * @param variant The name of the build variant. Used as a parameter to obtain relevant Gradle
 * tasks.
 * @param capitalisedVariantFlavorName The TitleCase representation of the Android app or library's
 * product flavor. Used as a parameter to obtain relevant Gradle tasks.
 */
class AsmFileTreeFetcher(
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
            getAsmClassesFileTree(
                "transform${capitalisedVariantName}ClassesWithAsm",
            ) ?: getAsmClassesFileTree(
                "transform${capitalisedVariantFlavorName}ClassesWithAsm",
            ) ?: project.fileTree(
                "${project.buildDir}/intermediates/asm_instrumented_project_classes/$variant/",
            )
        }.also {
            project.debugLog(
                "AsmFileTreeFetcher: ${it.get().files}",
            )
        }
    }

    private fun getAsmClassesFileTree(
        name: String,
    ): FileTree? = performOnFoundTask<TransformClassesWithAsmTask, FileTree>(name) {
        it.classesOutputDir.asFileTree
    }
}
