package uk.gov.ui.jacoco.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinCompileFileTreeFetcher(
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
            getKotlinCompileFileTree(
                "compile${capitalisedVariantName}Kotlin",
            ) ?: getKotlinCompileFileTree(
                "compile${capitalisedVariantFlavorName}Kotlin",
            ) ?: moduleProject.fileTree(
                "${moduleProject.buildDir}/tmp/kotlin-classes/$variantName",
            )
        }.also {
            println(
                "${moduleProject.name}: KotlinCompileFileTreeFetcher: ${it.get().files}",
            )
        }
    }

    private fun getKotlinCompileFileTree(
        name: String,
    ): FileTree? = performOnFoundTask<KotlinCompile, FileTree>(name) {
        it.destinationDirectory.asFileTree
    }
}
