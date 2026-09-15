package uk.gov.android.ui.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.provideDelegate
import uk.gov.pipelines.config.ApkConfig

fun CommonExtension.namespace(suffix: String, project: Project) {
    val apkConfig: ApkConfig by project.rootProject.extra
    namespace = "${apkConfig.applicationId}.$suffix"
}
