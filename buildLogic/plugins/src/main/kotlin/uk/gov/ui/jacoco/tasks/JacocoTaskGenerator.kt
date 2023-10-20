package uk.gov.ui.jacoco.tasks

import org.gradle.api.tasks.TaskProvider
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.jacoco.config.JacocoCustomConfig

interface JacocoTaskGenerator {
    val taskName: String
    val config: JacocoCustomConfig
    fun customTask(): TaskProvider<JacocoReport>
}
