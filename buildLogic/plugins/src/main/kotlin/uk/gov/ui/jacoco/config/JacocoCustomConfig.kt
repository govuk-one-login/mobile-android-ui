package uk.gov.ui.jacoco.config

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.configure
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.SourceSetFolder
import uk.gov.ui.jacoco.filetree.fetcher.FileTreeFetcher

abstract class JacocoCustomConfig(
    private val moduleProject: Project,
    private val classDirectoryFetcher: FileTreeFetcher,
    private val testTaskName: String,
) {

    abstract fun getExecutionData(): ConfigurableFileTree

    fun generateCustomJacocoReport(
        excludes: List<String>,
        dependencies: Iterable<*>,
        description: String,
        jacocoTestName: String,
        group: String = "jacoco",
        outputDir: String,
    ): TaskProvider<JacocoReport> {
        val sourceSetFolder = SourceSetFolder(moduleProject)

        val classDirectoriesTree = classDirectoryFetcher.getProvider(excludes)

        val customJacocoTask = moduleProject.tasks.register(
            jacocoTestName,
            JacocoReport::class.java,
        ) {
            this.dependsOn(
                dependencies,
            )
            this.description = description
            this.group = group

            this.additionalSourceDirs.from(
                sourceSetFolder.sourceFiles.files.map {
                    it.absolutePath
                },
            ).also {
                println(
                    "${project.name}: $jacocoTestName: Configured additional source directories: " +
                        "${it.files}",
                )
            }
            this.classDirectories.from(classDirectoriesTree).also {
                println(
                    "${project.name}: $jacocoTestName: Configured class directories: " +
                        "${it.files}",
                )
            }
            this.executionData.from(this@JacocoCustomConfig.getExecutionData()).also {
                println(
                    "${project.name}: $jacocoTestName: Configured execution data: " +
                        "${it.files}",
                )
            }
            this.sourceDirectories.from(sourceSetFolder.sourceFiles.files).also {
                println(
                    "${project.name}: $jacocoTestName: Configured source directories: " +
                        "${it.files}",
                )
            }

            this.setupReportsProperties(outputDir)
        }

        moduleProject.tasks.findByName(testTaskName)?.finalizedBy(customJacocoTask)

        return customJacocoTask
    }

    private fun JacocoReport.setupReportsProperties(outputDir: String) {
        this.reports {
            this.csv.apply {
                required.set(true)
                outputLocation.set(moduleProject.file("$outputDir/report.csv"))
            }
            this.xml.apply {
                required.set(true)
                outputLocation.set(moduleProject.file("$outputDir/report.xml"))
            }
            this.html.apply {
                required.set(true)
                outputLocation.set(moduleProject.file("$outputDir/html"))
            }
        }
    }
}
