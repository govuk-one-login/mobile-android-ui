package uk.gov.android.ui.componentsv2.camera.state.preview

import androidx.camera.core.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * [ImagePreviewer.Complete] implementation that defers to the internal [state].
 */
class MutableImagePreviewer(
    private val state: MutableStateFlow<Preview> = MutableStateFlow(Preview.Builder().build()),
) : ImagePreviewer.Complete {
    override val preview: StateFlow<Preview> = state
    override fun update(preview: Preview) {
        state.update { preview }
    }
}
