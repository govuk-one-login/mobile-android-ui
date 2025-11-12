package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.ImageAnalysis
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MutableImageAnalyzer(
    private val state: MutableStateFlow<ImageAnalysis?> = MutableStateFlow(null),
) : CameraContentState.ImageAnalyzer.Complete {
    override val imageAnalysis: StateFlow<ImageAnalysis?> = state
    override fun update(imageAnalysis: ImageAnalysis?) {
        state.update { imageAnalysis }
    }
}
