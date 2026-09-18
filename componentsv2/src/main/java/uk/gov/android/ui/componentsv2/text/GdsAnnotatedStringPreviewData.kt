package uk.gov.android.ui.componentsv2.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.m3.Typography

internal enum class GdsAnnotatedStringPreview {
    TrailingIcon,
    LeadingIcon,
    CustomTextStyle,
    BoldFontWeight,
    CustomColors,
}

internal data class GdsAnnotatedStringPreviewData(
    val text: String = "GdsAnnotatedString",
    val fontWeight: FontWeight = FontWeight.Normal,
    val icon: Int = R.drawable.ic_external_site,
    val iconId: Int = R.string.in_line_icon_id,
    val textStyle: TextStyle = Typography.labelLarge,
    val iconContentDescription: Int = R.string.icon_content_desc,
    val color: Color,
    val iconColor: Color? = null,
    val isIconTrailing: Boolean = true,
    val iconBackgroundColor: Color,
)

internal class GdsAnnotatedStringPreviewDataProvider :
    PreviewParameterProvider<GdsAnnotatedStringPreview> {
    override val values: Sequence<GdsAnnotatedStringPreview> =
        GdsAnnotatedStringPreview.entries.asSequence()
}

@Composable
internal fun GdsAnnotatedStringPreview.toData(): GdsAnnotatedStringPreviewData {
    val defaultParams = GdsAnnotatedStringPreviewData(
        text = "Default",
        color = MaterialTheme.colorScheme.onBackground,
        iconBackgroundColor = MaterialTheme.colorScheme.background,
    )

    return when (this) {
        GdsAnnotatedStringPreview.TrailingIcon ->
            defaultParams.copy(
                text = "Trailing icon",
                icon = R.drawable.ic_external_site,
                iconContentDescription = R.string.icon_content_desc,
            )

        GdsAnnotatedStringPreview.LeadingIcon ->
            defaultParams.copy(
                text = "Leading icon",
                icon = R.drawable.ic_error_filled,
                iconContentDescription = R.string.icon_content_desc,
                isIconTrailing = false,
            )

        GdsAnnotatedStringPreview.CustomTextStyle ->
            defaultParams.copy(
                text = "Custom text style",
                fontWeight = FontWeight.Normal,
                textStyle = ExtraTypography.bodyLargeBold,
            )

        GdsAnnotatedStringPreview.BoldFontWeight ->
            defaultParams.copy(
                text = "Bold font weight",
                fontWeight = FontWeight.Bold,
            )

        GdsAnnotatedStringPreview.CustomColors ->
            defaultParams.copy(
                text = "Custom colors",
                color = Color.Red,
                iconColor = Color.Green,
                iconBackgroundColor = Color.Magenta,
                textStyle = ExtraTypography.bodyLargeBold,
            )
    }
}
