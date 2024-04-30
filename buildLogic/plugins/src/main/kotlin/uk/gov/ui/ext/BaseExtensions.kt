package uk.gov.ui.ext

import com.android.build.api.dsl.ManagedVirtualDevice
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.tasks.ManagedDeviceInstrumentationTestTask
import com.android.build.gradle.internal.tasks.ManagedDeviceSetupTask
import java.io.ByteArrayOutputStream
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.maybeCreate
import org.gradle.kotlin.dsl.register
import uk.gov.ui.emulator.SystemImageSource
import uk.gov.ui.ext.StringExtensions.proseToUpperCamelCase

object BaseExtensions {
    private val filter = Regex("[/\\\\:<>\"?*| ()]")

    /**
     * Registers a task that defers to the `getAllHardwareProfileNames` script found within the
     * `.sh/` folder.
     *
     * Outputs all applicable hardware profiles available on the machine running this task.
     */
    fun BaseExtension.generateGetHardwareProfilesTask(
        project: Project,
        outputStream: ByteArrayOutputStream
    ) = project.tasks.register("getHardwareProfiles", Exec::class) {
        commandLine(
            "bash",
            "${project.rootProject.rootDir}/.sh/getAllHardwareProfileNames"
        )

        standardOutput = outputStream

        val consoleOutput: ByteArray by this.extra(outputStream.toByteArray())
    }

    /**
     * Creates a Gradle managed device within the associated Gradle project.
     *
     * This effectively creates a [ManagedDeviceSetupTask] to create, build and verify the initial
     * state of a new emulator. There is also a [ManagedDeviceInstrumentationTestTask] created,
     * respecting `${flavor}${buildType}AndroidTest` naming conventions.
     */
    fun BaseExtension.generateManagedDeviceConfiguration(
        hardwareProfile: String,
        apiLevel: Int,
        source: SystemImageSource
    ) {
        val managedDeviceName = generateDeviceName(hardwareProfile, source, apiLevel)

        testOptions {
            animationsDisabled = true
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
            managedDevices {
                devices {
                    maybeCreate<ManagedVirtualDevice>(
                        managedDeviceName
                    ).apply {
                        // Use device profiles you typically see in Android Studio.
                        this.device = hardwareProfile
                        // Use only API levels 27 and higher.
                        this.apiLevel = apiLevel
                        // To include Google services, use "google"
                        this.systemImageSource = source.image
                    }
                }
            }
        }
    }

    private fun generateDeviceName(
        hardwareProfile: String,
        source: SystemImageSource,
        apiLevel: Int
    ): String {
        val hardwareProfileTaskSegment = hardwareProfile.replace(
            filter,
            ""
        ).proseToUpperCamelCase()

        val systemImageSourceTaskSegment = source.sanitise()

        return systemImageSourceTaskSegment +
            "${hardwareProfileTaskSegment.capitalized()}Api$apiLevel"
    }

    /**
     * Loops through the provided parameters, deferring each entry to the
     * [generateManagedDeviceConfiguration] function.
     */
    fun BaseExtension.generateDeviceConfigurations(
        hardwareProfileStrings: Collection<String>,
        apiLevelRange: IntRange = (29..34),
        systemImageSources: Collection<SystemImageSource> = SystemImageSource.values().asList()
    ) {
        hardwareProfileStrings.forEach { hardwareProfileString ->
            apiLevelRange.forEach { apiLevel ->
                systemImageSources.forEach { systemImageSource ->
                    generateManagedDeviceConfiguration(
                        hardwareProfile = hardwareProfileString,
                        apiLevel = apiLevel,
                        source = systemImageSource
                    )
                }
            }
        }
    }
}
