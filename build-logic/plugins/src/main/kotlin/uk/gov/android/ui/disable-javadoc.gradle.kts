package uk.gov.android.ui

// https://github.com/Kotlin/dokka/issues/2956
tasks
    .matching { task ->
        task.name.contains("javaDocReleaseGeneration", ignoreCase = true) or
            task.name.contains("javaDocDebugGeneration")
    }.configureEach {
        enabled = false
    }
