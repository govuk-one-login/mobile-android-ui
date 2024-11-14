package uk.gov.android.ui.components.information

import androidx.compose.ui.Modifier
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.images.icon.IconParameters

data class InformationParameters(
    val contentParameters: ContentParameters,
    val iconParameters: IconParameters,
    val dpMarginBetweenIconAndContent: Number = 16,
    val modifier: Modifier = Modifier
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = contentParameters.getSubtitleEntry(resourceIndex)
}
