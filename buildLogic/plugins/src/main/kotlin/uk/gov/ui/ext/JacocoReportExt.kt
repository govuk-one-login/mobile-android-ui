package uk.gov.ui.ext

import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

/**
 * Wrapper object for containing functions that extend the [JacocoReport] Gradle task.
 */
object JacocoReportExt {

    /**
     * Declares the folders for a given [JacocoReport] task's output.
     *
     * @param project The Gradle project that acts as a baseline directory. This creates
     * files out of the provided [reportsOutputDir], with file type specific suffixes.
     */
    fun JacocoReport.setupReportDirectories(
        project: Project,
        reportsOutputDir: String,
    ) {
        this.reports {
            this.csv.apply {
                required.set(true)
                outputLocation.set(project.file("$reportsOutputDir/report.csv"))
            }
            this.xml.apply {
                required.set(true)
                outputLocation.set(project.file("$reportsOutputDir/report.xml"))
            }
            this.html.apply {
                required.set(true)
                outputLocation.set(project.file("$reportsOutputDir/html"))
            }
        }
    }
}