package uk.gov.android.ui.componentsv2.camera.state

import androidx.camera.core.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MutablePreviewer(
    private val state: MutableStateFlow<Preview> = MutableStateFlow(Preview.Builder().build()),
) : CameraContentState.Previewer.Complete {
    override val preview: StateFlow<Preview> = state
    override fun update(preview: Preview) {
        state.update { preview }
    }
}
