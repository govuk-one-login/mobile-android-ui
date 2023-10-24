package uk.gov.ui

import java.io.FilenameFilter

/**
 * Wrapper object that contains regular expression exclusion patterns for file names.
 */
object Filters {
    val android = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/*FileManager*",
        "**/*AndroidCamera*",
        "**/*AndroidBiometrics*",
        "**/*ContactsProvider*",
        "**/*IntentProvider*",
    )

    private val dataBindingFilters = listOf(
        "android/databinding/**/*.class",
        "**/android/databinding/*Binding.class",
        "**/android/databinding/*",
        "**/androidx/databinding/*",
        "**/databinding/*",
        "**/BR.*",
    )

    val kotlin = listOf(
        "**/*MapperImpl*.*",
        "**/*\$ViewInjector*.*",
        "**/*\$ViewBinder*.*",
        "**/BuildConfig.*",
        "**/*Component*.*",
        "**/*BR*.*",
        "**/Manifest*.*",
        "**/*\$Lambda$*.*",
        "**/*Companion*.*",
        "**/*MembersInjector*.*",
        "**/*_MembersInjector.class",
        "**/*_Factory*.*",
        "**/*_Provide*Factory*.*",
        "**/*Extensions*.*",
        "**/*Extension*.*",
        "**/*\$Result.*",
        "**/*\$Result$*.*",
    )

    val userInterfaces = listOf(
        "**/*Activity*",
        "**/*Adapter*",
        "**/*BindingAdapter*",
        "**/*Dialog*",
        "**/*DiffCallback*",
        "**/*Fragment*",
        "**/*ItemDecoration*",
        "**/*LayoutManager*",
        "**/*Service*",
        "**/*ViewHolder*",
    )

    val sonar = listOf(
        "*.json",
        "**/.gradle/**",
        "**/*.gradle*",
    )

    val androidInstrumentationTests = listOf(
        dataBindingFilters,
        android,
        kotlin,
    ).flatten()

    val androidUnitTests = listOf(
        androidInstrumentationTests,
        userInterfaces,
    ).flatten()

    val testSourceSets = listOf(
        "**/src/test/java/\$",
        "**/src/test*/java/\$",
        "**/src/androidTest*/java/\$",
        "**/src/androidTest/java/\$",
    )

    /**
     * [FilenameFilter] for filtering out any source set folders that contain 'test', such as
     * `main`.
     */
    val sourceFilenameFilter = FilenameFilter { parentFile, fileName ->
        parentFile != null &&
            parentFile.isDirectory &&
            !(fileName?.contains("test", ignoreCase = true) ?: false)
    }

    /**
     * [FilenameFilter] for obtaining all source set folders that contain 'test', such as
     * `androidTest`.
     */
    val testFilenameFilter = FilenameFilter { parentFile, fileName ->
        parentFile != null &&
            parentFile.isDirectory &&
            (fileName?.contains("test", ignoreCase = true) ?: false)
    }
}
