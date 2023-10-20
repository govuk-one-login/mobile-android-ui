package uk.gov.ui.jacoco.filetree.fetcher

import com.android.build.gradle.tasks.TransformClassesWithAsmTask
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider

class AsmFileTreeFetcher(
    moduleProject: Project,
    variantName: String,
    capitalisedVariantFlavorName: String,
) : BaseFileTreeFetcher(
    moduleProject,
    variantName,
    capitalisedVariantFlavorName,
) {

    override fun getBaseFileTree(): Provider<FileTree> {
        return moduleProject.provider {
            getAsmClassesFileTree(
                "transform${capitalisedVariantName}ClassesWithAsm",
            ) ?: getAsmClassesFileTree(
                "transform${capitalisedVariantFlavorName}ClassesWithAsm",
            ) ?: moduleProject.fileTree(
                "${moduleProject.buildDir}/intermediates/asm_instrumented_project_classes/$variantName/",
            )
        }.also {
            println(
                "${moduleProject.name}: AsmFileTreeFetcher: ${it.get().files}",
            )
        }
    }

    private fun getAsmClassesFileTree(
        name: String,
    ): FileTree? = performOnFoundTask<TransformClassesWithAsmTask, FileTree>(name) {
        it.classesOutputDir.asFileTree
    }
}
