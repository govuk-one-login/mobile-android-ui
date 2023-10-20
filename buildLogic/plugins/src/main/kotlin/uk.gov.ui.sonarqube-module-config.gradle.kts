import org.sonarqube.gradle.SonarExtension
import uk.gov.ui.Filters
import uk.gov.ui.SourceSetFolder

plugins {
    id("org.sonarqube")
}

fun generateCommaSeparatedFiles(
    iterator: Iterable<String>,
) = fileTree(project.projectDir) {
    this.setIncludes(iterator)
}.files.joinToString(
    separator = ",",
    transform = File::getAbsolutePath,
)

private val _owaspDependencyCheckBase by project.extra(
    "${project.buildDir}/reports/dependency-check-report",
)
val androidLintReportFiles by project.extra(
    generateCommaSeparatedFiles(listOf("**/reports/lint-results-*.xml")),
)
val detektReportFiles by project.extra(
    generateCommaSeparatedFiles(listOf("**/reports/detekt/*.xml")),
)
val jacocoXmlReportFiles by project.extra(
    generateCommaSeparatedFiles(
        listOf(
            "**/reports/coverage/**/*.xml", // android instrumentation test reports
            "**/reports/jacoco/**/*.xml", // unit test reports
        ),
    ),
)
val junitReportFiles by project.extra(
    generateCommaSeparatedFiles(
        listOf(
            "**/outputs/androidTest-results/connected/flavors/*/", // instrumentation
            "**/test-results", // unit tests
        ),
    ),
)
val ktLintReportFiles by project.extra(
    generateCommaSeparatedFiles(listOf("**/reports/ktlint/**/*.xml")),
)
val owaspDependencyCheckHtml by project.extra(
    "$_owaspDependencyCheckBase.html",
)
val owaspDependencyCheckJson by project.extra(
    "$_owaspDependencyCheckBase.json",
)
val sonarExclusions by project.extra(
    listOf(
        Filters.androidInstrumentationTests,
        Filters.sonar,
        Filters.testSourceSets,
    ).flatten().joinToString(separator = ","),
)

val moduleSourceFolder = SourceSetFolder(project)
var sourceFolders by project.extra("")
var testFolders by project.extra("")

var projectSonarProperties by project.extra(
    mapOf<String, Any>(),
)

configure<SonarExtension> {
    if (moduleSourceFolder.srcExists()) {
        sourceFolders = moduleSourceFolder.sourceFolders
        testFolders = moduleSourceFolder.testFolders
    }

    projectSonarProperties = mapOf<String, Any>(
        "sonar.sources" to sourceFolders,
        "sonar.tests" to testFolders,
        "sonar.exclusions" to sonarExclusions,
        "sonar.androidLint.reportPaths" to androidLintReportFiles,
        "sonar.coverage.jacoco.xmlReportPaths" to jacocoXmlReportFiles,
        "sonar.kotlin.detekt.reportPaths" to detektReportFiles,
        "sonar.kotlin.ktlint.reportPaths" to ktLintReportFiles,
        "sonar.junit.reportPaths" to junitReportFiles,
        "sonar.dependencyCheck.htmlReportPath" to owaspDependencyCheckHtml,
        "sonar.dependencyCheck.jsonReportPath" to owaspDependencyCheckJson,
    )

    properties {
        projectSonarProperties.forEach { (key: String, value: Any) ->
            property(key, value)
        }
    }
}
