package uk.gov.android.ui.componentsv2.listcomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.theme.m3.Backgrounds
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.toMappedColors
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding

@Suppress("LongMethod")
@Composable
fun ListComponent(
    title: String,
    modifier: Modifier = Modifier,
    leadingImage: Image? = null,
    subtitle: String? = null,
    trailingText: String? = null,
    trailingIcon: ListComponentTrailingIcon? = null,
    showDivider: Boolean = true,
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
                    end = mediumPadding,
                )
                .fillMaxWidth(),
        ) {
            leadingImage?.let {
                Image(
                    imageVector = ImageVector.vectorResource(it.drawable),
                    contentDescription = it.contentDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(width = 42.dp, height = 30.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .padding(end = smallPadding),
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = title, color = Red, style = Typography.bodyLarge)
                subtitle?.let {
                    Text(text = subtitle, color = Red, style = Typography.bodySmall)
                }
            }
            trailingText?.let {
                Text(
                    text = trailingText,
                    color = Red,
                    style = Typography.bodySmall,
                    maxLines = 1,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(start = 16.dp),
                )
            }
            trailingIcon?.let { icon ->
                when (icon) {
                    is ListComponentTrailingIcon.NavigateNext -> {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_dot),
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(width = 24.dp, height = 24.dp)
                                .align(alignment = Alignment.CenterVertically)
                                .padding(start = smallPadding),
                        )
                    }

                    is ListComponentTrailingIcon.OpenInNew -> Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_external_site),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(width = 24.dp, height = 24.dp)
                            .align(alignment = Alignment.CenterVertically)
                            .padding(start = smallPadding),
                    )

                    is ListComponentTrailingIcon.Switch ->
                        Switch(
                            checked = icon.checked,
                            onCheckedChange = { icon.onToggle },
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = smallPadding),
                        )
                }
            }
        }
        if (showDivider) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Red,
                modifier = Modifier,
            )
        }
    }
}

@Composable
@PreviewLightDark
internal fun ListComponentPreview() {
    GdsTheme {
        ListComponent(
            title = "Hello",
            leadingImage = Image(
                R.drawable.arrow_forward,
                "HELLO",
            ),
            subtitle = "Subtitle text that might be a bit long and span multiple lines",
            trailingText = "100+",
            trailingIcon = ListComponentTrailingIcon.Switch(true) { println("") },
            showDivider = true,
        )
    }
}
