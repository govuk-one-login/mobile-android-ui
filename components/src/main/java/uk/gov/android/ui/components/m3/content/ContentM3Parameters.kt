package uk.gov.android.ui.components.m3.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.m3.HeadingSizeM3
import uk.gov.android.ui.theme.smallPadding

data class ContentM3Parameters(
    val resource: List<GdsContentText>,
    val color: Color? = null,
    val headingSize: HeadingSizeM3 = HeadingSizeM3.H4(),
    val textStyle: TextStyle? = null,
    val modifier: Modifier = Modifier,
    val internalColumnModifier: Modifier =
        Modifier.padding(bottom = smallPadding),
    val textAlign: TextAlign? = TextAlign.Center,
    val headingModifier: Modifier = Modifier.fillMaxWidth(),
    val textModifier: Modifier = Modifier.fillMaxWidth()
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = resource[resourceIndex].subTitle

    override fun toString(): String = this::class.java.simpleName
}
