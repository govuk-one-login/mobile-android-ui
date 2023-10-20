package uk.gov.ui.jacoco.config

import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.TaskProvider
import org.gradle.testing.jacoco.tasks.JacocoReport
import uk.gov.ui.SourceSetFolder
import uk.gov.ui.filetree.fetcher.FileTreeFetcher

abstract class JacocoCustomConfig(
    private val moduleProject: Project,
    private val classDirectoryFetcher: FileTreeFetcher,
) {

    abstract fun getExecutionData(): FileTree

    private var _createdTask: TaskProvider<JacocoReport>? = null
    val createdTask: TaskProvider<JacocoReport> get() = _createdTask!!

    fun generateCustomJacocoReport(
        excludes: List<String>,
        dependencies: Iterable<*>,
        description: String,
        taskName: String,
        testTaskName: String,
        group: String = "jacoco",
        outputDir: String,
    ): TaskProvider<JacocoReport> {
        generateCustomJacocoReport(
            excludes,
            dependencies,
            description,
            taskName,
            group,
            outputDir,
        )

        moduleProject.tasks.findByName(testTaskName)?.finalizedBy(createdTask)

        return createdTask
    }

    fun generateCustomJacocoReport(
        excludes: List<String>,
        dependencies: Iterable<*>,
        description: String,
        taskName: String,
        group: String = "jacoco",
        outputDir: String,
    ): TaskProvider<JacocoReport> {
        val sourceSetFolder = SourceSetFolder(moduleProject)

        val classDirectoriesTree = classDirectoryFetcher.getProvider(excludes)

        _createdTask = if (moduleProject.tasks.findByName(taskName) != null) {
            moduleProject.tasks.withType(JacocoReport::class.java).named(taskName)
        } else {
            moduleProject.tasks.register(
                taskName,
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
                        "${project.name}: $taskName: Configured additional source directories: " +
                            "${it.files}",
                    )
                }
                this.classDirectories.from(classDirectoriesTree).also {
                    println(
                        "${project.name}: $taskName: Configured class directories: " +
                            "${it.files}",
                    )
                }
                this.executionData.from(this@JacocoCustomConfig.getExecutionData()).also {
                    println(
                        "${project.name}: $taskName: Configured execution data: " +
                            "${it.files}",
                    )
                }
                this.sourceDirectories.from(sourceSetFolder.sourceFiles.files).also {
                    println(
                        "${project.name}: $taskName: Configured source directories: " +
                            "${it.files}",
                    )
                }

                this.setupReportsProperties(outputDir)
            }
        }

        return createdTask
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
