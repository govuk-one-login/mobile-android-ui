package uk.gov.ui.components.information

import androidx.annotation.Keep
import androidx.compose.ui.Modifier
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.images.icon.IconParameters

@Keep
data class InformationParameters(
    val contentParameters: ContentParameters,
    val iconParameters: IconParameters,
    val dpMarginBetweenIconAndContent: Number = 16,
    val modifier: Modifier = Modifier
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = contentParameters.getSubtitleEntry(resourceIndex)
}
