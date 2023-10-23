package uk.gov.ui.ext

import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

fun Test.decorateTestTasksWithJacoco() {
    if (this.hasProperty("jacoco")) {
        (this.property("jacoco") as JacocoTaskExtension).let {
            it.isIncludeNoLocationClasses = true
            it.excludes = listOf("jdk.internal.*")
        }
    }
}
