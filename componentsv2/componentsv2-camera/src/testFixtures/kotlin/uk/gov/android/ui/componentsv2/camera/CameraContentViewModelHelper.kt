package uk.gov.android.ui.componentsv2.camera

import android.annotation.SuppressLint
import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraInfo
import androidx.camera.core.impl.CameraConfig
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope

object CameraContentViewModelHelper {
    object DummyCamera : Camera {
        private val exception = IllegalAccessException(
            "This is a dummy camera and shouldn't be interacted with!",
        )
        override fun getCameraControl(): CameraControl {
            throw exception
        }

        override fun getCameraInfo(): CameraInfo {
            throw exception
        }

        @SuppressLint("RestrictedApi")
        override fun getExtendedConfig(): CameraConfig {
            throw exception
        }
    }
    fun TestScope.monitor(model: CameraContentViewModel) {
        listOf(
            model.surfaceRequest,
            model.preview,
            model.imageAnalysis,
            model.imageCapture,
            model.camera,
        ).forEach { flow ->
            backgroundScope.launch {
                flow.collect {}
            }
        }
    }
}
