package uk.gov.ui

import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import java.io.File

class SourceSetFolder(private val project: Project) {

    private val src: File = File("${project.projectDir}/src")

    val sourceFolders: String
        get() = (
            src.listFiles(Filters.sourceFilenameFilter)
                ?.filterNotNull()
                ?.joinToString(separator = ",") { it.absolutePath }
                ?: ""
            ).also {
            println("${project.name}: SourceSetFolder: Source folders: $it")
        }

    val sourceFiles: ConfigurableFileCollection get() = project.files(sourceFolders)
    val testFiles: ConfigurableFileCollection get() = project.files(testFolders)

    val testFolders: String
        get() = (
            src.listFiles(Filters.testFilenameFilter)
                ?.filterNotNull()
                ?.joinToString(separator = ",") { it.absolutePath }
                ?: ""
            ).also {
            println("${project.name}: SourceSetFolder: Test folders: $it")
        }

    fun srcExists(): Boolean = src.exists()
}
