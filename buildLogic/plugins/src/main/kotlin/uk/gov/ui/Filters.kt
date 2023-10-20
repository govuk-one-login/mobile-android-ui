package uk.gov.ui

import java.io.FilenameFilter

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

    val dataBinding = listOf(
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
        dataBinding,
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

    val sourceFilenameFilter = FilenameFilter { parentFile, fileName ->
        parentFile != null &&
            parentFile.isDirectory &&
            !(fileName?.contains("test", ignoreCase = true) ?: false)
    }

    val testFilenameFilter = FilenameFilter { parentFile, fileName ->
        parentFile != null &&
            parentFile.isDirectory &&
            (fileName?.contains("test", ignoreCase = true) ?: false)
    }
}
