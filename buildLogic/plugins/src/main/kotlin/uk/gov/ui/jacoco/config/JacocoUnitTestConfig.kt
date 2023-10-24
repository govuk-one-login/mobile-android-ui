package uk.gov.ui.jacoco.config

import com.android.build.gradle.tasks.factory.AndroidUnitTest
import org.gradle.api.Project
import org.gradle.api.file.FileTree
import org.gradle.kotlin.dsl.named
import uk.gov.ui.filetree.fetcher.FileTreeFetcher

/**
 * A [JacocoCustomConfig] implementation specifically for unit tests.
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
class JacocoUnitTestConfig(
    private val project: Project,
    private val classDirectoryFetcher: FileTreeFetcher,
    private val capitalisedVariantName: String,
) : JacocoCustomConfig(
    project,
    classDirectoryFetcher,
) {

    override fun getExecutionData(): FileTree {
        val unitTestTask = project.tasks.named(
            "test${capitalisedVariantName}UnitTest",
            AndroidUnitTest::class,
        )
        val unitTestExecutionDataFile = unitTestTask.flatMap { utTask ->
            project.provider {
                utTask.jacocoCoverageOutputFile.get().asFile
                    .parentFile
                    .absolutePath
            }
        }
        return project.fileTree(unitTestExecutionDataFile) {
            setIncludes(listOf("${unitTestTask.name}.exec"))
        }
    }
}