package uk.gov.ui

import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import java.io.File
import uk.gov.ui.ext.ProjectExt.debugLog

/**
 * Handles the logic for obtaining source set folders.
 *
 * @param project The Gradle [Project] that contain source set folders within `./src`.
 */
class SourceSetFolder(private val project: Project) {

    /**
     * The expected location of a Gradle module's source code folder.
     */
    private val src: File = File("${project.projectDir}/src")

    /**
     * The production code source set folders, provided as a comma-delimited string.
     */
    val sourceFolders: String
        get() = (
            src.listFiles(Filters.sourceFilenameFilter)
                ?.filterNotNull()
                ?.joinToString(separator = ",") { it.absolutePath }
                ?: ""
            ).also {
                project.debugLog("SourceSetFolder: Source folders: $it")
        }

    /**
     * The production code source set folders, provided as a File collection.
     */
    val sourceFiles: ConfigurableFileCollection get() = project.files(sourceFolders)

    /**
     * The test code source set folders, provided as a File collection.
     */
    val testFiles: ConfigurableFileCollection get() = project.files(testFolders)

    /**
     * The test code source set folders, provided as a comma-delimited string.
     */
    val testFolders: String
        get() = (
            src.listFiles(Filters.testFilenameFilter)
                ?.filterNotNull()
                ?.joinToString(separator = ",") { it.absolutePath }
                ?: ""
            ).also {
                project.debugLog("SourceSetFolder: Test folders: $it")
        }

    /**
     * Checks to see whether the [source][src] folder exists within the [project].
     */
    fun srcExists(): Boolean = src.exists()
}
