package uk.gov.android.ui.extensions

import org.gradle.api.Project

/**
 * Temporary workaround for https://github.com/Kotlin/dokka/issues/2956.
 * Disables javadoc generation tasks that fail due to Dokka compatibility issues.
 * Remove after upgrading to AGP 9.3-alpha05.
 */
fun Project.disableJavadocGeneration() {
    tasks
        .matching { task ->
            task.name.contains("javaDocReleaseGeneration", ignoreCase = true) ||
                task.name.contains("javaDocDebugGeneration")
        }.configureEach {
            enabled = false
        }
}
