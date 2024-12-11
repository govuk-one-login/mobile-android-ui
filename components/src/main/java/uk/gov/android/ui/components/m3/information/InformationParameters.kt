package uk.gov.android.ui.components.m3.information

import androidx.compose.ui.Modifier
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.content.ContentParameters

data class InformationParameters(
    val contentParameters: ContentParameters,
    val iconParameters: IconParameters,
    val dpMarginBetweenIconAndContent: Number = 16,
    val modifier: Modifier = Modifier,
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = contentParameters.getSubtitleEntry(resourceIndex)
}
