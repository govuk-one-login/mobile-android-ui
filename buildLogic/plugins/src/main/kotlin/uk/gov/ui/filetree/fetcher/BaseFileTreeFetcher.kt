package uk.gov.ui.filetree.fetcher

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.FileTree
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.util.PatternSet
import org.gradle.configurationcache.extensions.capitalized
import uk.gov.ui.ext.ProjectExt.debugLog

/**
 * Partial implementation for obtaining a [FileTree].
 *
 * Provides an implementation of [FileTreeFetcher.getProvider] that in turn defers to the abstract
 * function [getBaseFileTree]. This means a singular definition for a [FileTree]'s exclusion
 * handling.
 *
 * @param project The Gradle [Project] to base the resulting [FileTree] off of.
 * @param variant The name of the Android app or library's build variant.
 * @param capitalisedVariantFlavorName The TitleCase representation of the Android app or library's
 * product flavor.
 */
abstract class BaseFileTreeFetcher(
    val project: Project,
    val variant: String,
    val capitalisedVariantFlavorName: String,
) : FileTreeFetcher {

    /**
     * The TitleCase representation of the Android app or library's build variant.
     */
    protected val capitalisedVariantName = variant.capitalized()

    /**
     * Obtain the original [FileTree] for an implementation. This has no filtration on the object.
     */
    protected abstract fun getBaseFileTree(): Provider<FileTree>

    /**
     * Run the higher-order function stored within [action] on the [name]d task.
     *
     * This commonly obtains the base [FileTree] based on the [TaskType].
     *
     * @param TaskType The data type of the found task.
     * @param Result the return type of the function. This is commonly a [FileTree].
     * @param name The string name of the task.
     * @param action The additional handling required for the [TaskType].
     */
    inline fun <reified TaskType : Task, Result : Any> performOnFoundTask(
        name: String,
        noinline action: (TaskType) -> Result,
    ): Result? {
        return project.tasks.withType(TaskType::class.java).firstOrNull {
            it.name == name
        }.also {
            project.debugLog(
                "${this::class.java.simpleName}: found task: ${it?.name}",
            )
        }?.let(action::invoke)
    }

    override fun getProvider(
        excludes: List<String>,
    ): Provider<FileTree> {
        return project.provider {
            getBaseFileTree().get().matching(PatternSet().exclude(excludes))
        }
    }
}
