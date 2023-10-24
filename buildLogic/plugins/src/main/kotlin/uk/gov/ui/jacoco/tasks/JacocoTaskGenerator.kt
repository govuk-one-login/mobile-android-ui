package uk.gov.ui.jacoco.tasks

import org.gradle.api.tasks.TaskProvider
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.jacoco.config.JacocoCustomConfig

/**
 * Abstraction that acts as the entry point for generating a customised [JacocoReport] Gradle task.
 *
 * @property name The name of the generated [JacocoReport] Gradle task.
 * @property configuration The properties for generating a [JacocoReport] Gradle task.
 */
interface JacocoTaskGenerator {
    val name: String
    val configuration: JacocoCustomConfig

    /**
     * Registers a [JacocoReport] Gradle task to a given project.
     */
    fun customTask(): TaskProvider<JacocoReport>
}
