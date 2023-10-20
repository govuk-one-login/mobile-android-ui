package uk.gov.ui.jacoco.config

import com.android.build.gradle.tasks.factory.AndroidUnitTest
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.kotlin.dsl.named
import uk.gov.ui.jacoco.filetree.fetcher.FileTreeFetcher

class JacocoUnitTestConfig(
    private val project: Project,
    private val classDirectoryFetcher: FileTreeFetcher,
    private val capitalisedVariantName: String,
    private val testTaskName: String
) : JacocoCustomConfig(
    project,
    classDirectoryFetcher,
    testTaskName
) {

    override fun getExecutionData(): ConfigurableFileTree {
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