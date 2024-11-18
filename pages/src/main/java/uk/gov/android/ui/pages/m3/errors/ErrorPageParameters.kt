package uk.gov.android.ui.pages.m3.errors

import androidx.compose.ui.Modifier
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.information.InformationParameters

data class ErrorPageParameters(
    val primaryButtonParameters: ButtonParameters,
    val informationParameters: InformationParameters,
    val secondaryButtonParameters: ButtonParameters? = null,
    val modifier: Modifier = Modifier
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = informationParameters.getSubtitleEntry(
        resourceIndex
    )
    fun hasSecondaryButton(): Boolean = secondaryButtonParameters != null
}
