package uk.gov.ui.jacoco.config

import com.android.build.gradle.internal.tasks.DeviceProviderInstrumentTestTask
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.named
import uk.gov.ui.filetree.fetcher.FileTreeFetcher

class JacocoConnectedTestConfig(
    private val project: Project,
    private val classDirectoryFetcher: FileTreeFetcher,
    private val capitalisedVariantName: String,
) : JacocoCustomConfig(
    project,
    classDirectoryFetcher,
) {

    override fun getExecutionData(): FileTree {
        val connectedTestTask: TaskProvider<DeviceProviderInstrumentTestTask> = project.tasks.named(
            "connected${capitalisedVariantName}AndroidTest",
            DeviceProviderInstrumentTestTask::class,
        )
        val connectedTestExecutionDirectory = connectedTestTask.flatMap { connectedTask ->
            project.provider {
                connectedTask
                    .coverageDirectory
                    .asFile
                    .get()
                    .absolutePath
            }
        }

        return project.fileTree(connectedTestExecutionDirectory) {
            setIncludes(listOf("**/*.ec"))
        }
    }
}