package uk.gov.android.ui.components.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import uk.gov.android.ui.components.HeadingSize
import uk.gov.android.ui.components.HeadingSize.H4
import uk.gov.android.ui.theme.smallPadding

data class ContentParameters(
    val resource: List<GdsContentText>,
    val color: Color? = null,
    val headingSize: HeadingSize = H4(),
    val textStyle: TextStyle? = null,
    val modifier: Modifier = Modifier,
    val internalColumnModifier: Modifier =
        Modifier.padding(bottom = smallPadding),
    val textAlign: TextAlign? = TextAlign.Center,
    val headingModifier: Modifier = Modifier.fillMaxWidth(),
    val textModifier: Modifier = Modifier.fillMaxWidth(),
    val headingPadding: PaddingValues = PaddingValues(),
    val textPadding: PaddingValues = PaddingValues()
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = resource[resourceIndex].subTitle

    override fun toString(): String = this::class.java.simpleName
}
