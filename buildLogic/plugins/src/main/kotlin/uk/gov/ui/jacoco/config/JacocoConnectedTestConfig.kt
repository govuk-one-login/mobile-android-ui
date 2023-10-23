package uk.gov.ui.jacoco.config

import com.android.build.gradle.internal.tasks.DeviceProviderInstrumentTestTask
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.named
import uk.gov.ui.filetree.fetcher.FileTreeFetcher

/**
 * A [JacocoCustomConfig] implementation specifically for instrumentation tests.
 *
 * Obtains the required Gradle task via the provided [capitalisedVariantName], passed back through
 * the [getExecutionData] function.
 *
 * @param project The Gradle project that contains the required test task. Also generates the
 * return [FileTree].
 * @param classDirectoryFetcher The [FileTreeFetcher] that provides the class directories used for
 * reporting code coverage through Jacoco.
 * @param capitalisedVariantName The TitleCase representation of the Android build variant of the
 * Gradle module. Finds the relevant test task name by using this parameter.
 */
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