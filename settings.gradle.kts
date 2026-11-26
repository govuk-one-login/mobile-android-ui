pluginManagement {
    includeBuild("${rootProject.projectDir}/build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "mobile-android-ui"
listOf(
    ":accessibility-testing",
    ":componentsv2",
    ":componentsv2:componentsv2-camera",
    ":patterns",
    ":patterns:patterns-camera",
    ":theme",
    ":uitestwrapper",
).forEach(::include)
