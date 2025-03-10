package uk.gov.android.ui.componentsv2.supportingtext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.spacingDouble

@Composable
fun GdsSupportingText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        style = Typography.labelMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacingDouble),
        textAlign = textAlign,
    )
}
