package uk.gov.android.ui.componentsv2.listcomponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.toMappedColors
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding


@Composable
fun ListComponent(
    title: String,
    modifier: Modifier = Modifier,
    leadingImage: Image? = null,
    subtitle: String? = null,
    trailingText: String? = null,
    trailingIcon: ListComponentTrailingIcon? = null,
    showDivider: Boolean = true
) {
    Card(
        modifier =
            modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardColors(
            containerColor = Backgrounds.card.toMappedColors(),
            contentColor = Backgrounds.card.toMappedColors(),
            disabledContainerColor = Backgrounds.card.toMappedColors(),
            disabledContentColor = Backgrounds.card.toMappedColors(),
        ),
        elevation = CardDefaults.cardElevation(),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    top = 12.dp,
                    bottom = 12.dp,
                    start = smallPadding,
                    end = mediumPadding)
                .fillMaxWidth(),
        ) {
            leadingImage?.let {
                Image(
                    imageVector = ImageVector.vectorResource(it.drawable),
                    contentDescription = it.contentDescription,
                    modifier = Modifier
                        .size(width = 42.dp, height = 42.dp)
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
internal fun ListComponentPreview() {
    GdsTheme {
        ListComponent(
            title = "Hello",
            leadingImage = Image(R.drawable.arrow_forward, ""),
        )
    }
}
