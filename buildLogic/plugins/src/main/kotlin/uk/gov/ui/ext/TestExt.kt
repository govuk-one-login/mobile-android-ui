package uk.gov.ui.ext

import org.gradle.api.tasks.testing.Test
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension

/**
 * Wrapper object for containing extension functions for Gradle's [Test] task type.
 */
object TestExt {

    /**
     * Adds Jacoco exclusion rules to a Gradle library. Also includes classes that have no location
     * declared, yet still exist.
     *
     * No change occurs if there's no Jacoco property on the decorated task.
     */
    fun Test.decorateTestTasksWithJacoco() {
        if (this.hasProperty("jacoco")) {
            (this.property("jacoco") as JacocoTaskExtension).let {
                it.isIncludeNoLocationClasses = true
                it.excludes = listOf("jdk.internal.*")
            }
        }
    }
}
