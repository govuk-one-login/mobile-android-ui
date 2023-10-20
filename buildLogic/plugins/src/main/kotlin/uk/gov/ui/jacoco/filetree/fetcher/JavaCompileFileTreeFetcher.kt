package uk.gov.ui.jacoco.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.compile.JavaCompile

class JavaCompileFileTreeFetcher(
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
            getJavaCompileFileTree(
                "compile${capitalisedVariantName}JavaWithJavac",
            ) ?: getJavaCompileFileTree(
                "compile${capitalisedVariantFlavorName}JavaWithJavac",
            ) ?: moduleProject.fileTree(
                "${moduleProject.buildDir}/intermediates/javac/$variantName/classes",
            )
        }.also {
            println(
                "${moduleProject.name}: JavaCompileFileTreeFetcher: ${it.get().files}"
            )
        }
    }

    private fun getJavaCompileFileTree(
        name: String,
    ): FileTree? = performOnFoundTask<JavaCompile, FileTree>(name) {
        it.destinationDirectory.asFileTree
    }
}
