package uk.gov.ui.jacoco.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.util.PatternSet
import org.gradle.configurationcache.extensions.capitalized

abstract class BaseFileTreeFetcher(
    val moduleProject: Project,
    val variantName: String,
    val capitalisedVariantFlavorName: String
) : FileTreeFetcher {

    protected val capitalisedVariantName = variantName.capitalized()
    protected abstract fun getBaseFileTree(): Provider<FileTree>

    inline fun <reified TaskType : Task, Result : Any> performOnFoundTask(
        name: String,
        noinline action: (TaskType) -> Result,
    ): Result? {
        return moduleProject.tasks.withType(TaskType::class.java).firstOrNull {
            it.name == name
        }.also {
            println(
                "${moduleProject.name}: ${this::class.java.simpleName}: found task: ${it?.name}"
            )
        }?.let(action::invoke)
    }

    override fun getProvider(
        excludes: List<String>,
    ): Provider<FileTree> {
        return moduleProject.provider {
            getBaseFileTree().get().matching(PatternSet().exclude(excludes))
        }
    }
}
