package uk.gov.ui.ext

import org.gradle.api.Project

fun Project.debugLog(messageSuffix: String) {
    logger.debug("${project.path}: $messageSuffix")
}