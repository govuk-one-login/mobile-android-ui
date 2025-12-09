package uk.gov.android.ui.componentsv2.camera.state.capture

import androidx.camera.core.ImageCapture
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * [ImageCapturer.Complete] implementation that defers to the internal [state].
 */
class MutableImageCapturer(
    private val state: MutableStateFlow<ImageCapture?> = MutableStateFlow(null),
) : ImageCapturer.Complete {
    override val imageCapture: StateFlow<ImageCapture?> = state
    override fun update(imageCapture: ImageCapture?) {
        state.update { imageCapture }
    }
}
