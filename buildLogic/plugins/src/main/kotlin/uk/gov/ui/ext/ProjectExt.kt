package uk.gov.ui.ext

import org.gradle.api.Project

/**
 * Wrapper object for containing functions that extend a Gradle [Project].
 */
object ProjectExt {

    /**
     * Prints to a [Project]'s debug log.
     *
     * All logs are internally prefixed with the [path][Project.getPath] variable, similar to how
     * Gradle prefixes running tasks with it's path. For example: `:theme: Hello, world!`
     */
    fun Project.debugLog(messageSuffix: String) {
        logger.debug("${project.path}: $messageSuffix")
    }
}
