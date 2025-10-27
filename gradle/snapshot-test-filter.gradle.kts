// Apply snapshot test filtering logic after task graph is ready
gradle.taskGraph.whenReady {
    val snapshotKeywords = listOf("recordPaparazzi", "verifyPaparazzi")
    val isSnapshotTask = allTasks.any { task -> snapshotKeywords.any(task.name::contains) }
    logger.lifecycle("🔍 Snapshot task detected: $isSnapshotTask")

    tasks.withType(Test::class.java).configureEach {
        doFirst {
            if (isSnapshotTask) {
                logger.lifecycle("📸 Running snapshot tests only in task: $name")
                include("**/*ScreenshotTest.class")
            } else {
                logger.lifecycle("🚫 Excluding snapshot tests in task: $name")
                exclude("**/*ScreenshotTest.class")
            }
        }
    }
}
