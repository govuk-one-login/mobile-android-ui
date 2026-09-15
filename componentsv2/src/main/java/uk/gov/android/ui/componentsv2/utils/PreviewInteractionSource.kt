package uk.gov.android.ui.componentsv2.utils

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class PreviewInteractionSource(val interaction: Interaction? = null) : MutableInteractionSource {
    override val interactions: Flow<Interaction>
        get() = listOfNotNull(interaction).asFlow()

    override suspend fun emit(interaction: Interaction) = error("Not implemented for previews")

    override fun tryEmit(interaction: Interaction): Boolean = error("Not implemented for previews")
}
