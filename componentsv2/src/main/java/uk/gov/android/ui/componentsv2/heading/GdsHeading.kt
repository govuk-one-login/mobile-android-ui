package uk.gov.android.ui.componentsv2.heading

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.dark_theme_onBackground
import uk.gov.android.ui.theme.m3.light_theme_onBackground
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

sealed class GdsHeadingStyle(val style: TextStyle) {
    data object LargeTitle : GdsHeadingStyle(Typography.displaySmall)
    data object Title1 : GdsHeadingStyle(Typography.headlineLarge)
    data object Title2 : GdsHeadingStyle(Typography.headlineMedium)
    data object Title3 : GdsHeadingStyle(Typography.headlineSmall)
    data object Body : GdsHeadingStyle(Typography.bodyLarge)
    data class Custom(val customStyle: TextStyle) : GdsHeadingStyle(customStyle)
}

enum class GdsHeadingAlignment {
    CenterAligned,
    LeftAligned,
    RightAligned,
}

/**
 * GDS Heading
 *
 * @param text The visible heading text to be displayed. Default content description is the same as this text.
 * @param modifier Modifier to be applied to the heading layout.
 * @param customContentDescription Content description for accessibility (TalkBack) in case this needs to be different from the visible text.
 *    If provided, it will override the default content description derived from [text].
 * @param style The visual style of the heading (e.g., large title, section heading).
 * @param textAlign How the text should be aligned within its container.
 * @param textColour The color of the text. Defaults to the theme's `onBackground`.
 * @param textFontWeight Font weight override for the heading text (optional).
 */
@UnstableDesignSystemAPI
@Composable
fun GdsHeading(
    text: String,
    modifier: Modifier = Modifier,
    customContentDescription: String? = null,
    style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
    textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    textColour: Color = MaterialTheme.colorScheme.onBackground,
    textFontWeight: FontWeight? = null,
) {
    val typography = when (style) {
        GdsHeadingStyle.LargeTitle -> Typography.displaySmall
        GdsHeadingStyle.Title1 -> Typography.headlineLarge
        GdsHeadingStyle.Title2 -> Typography.headlineMedium
        GdsHeadingStyle.Title3 -> Typography.headlineSmall
        GdsHeadingStyle.Body -> Typography.bodyLarge
        is GdsHeadingStyle.Custom -> style.customStyle
    }

    val fontWeight = when (style) {
        GdsHeadingStyle.Body -> FontWeight.Bold
        else -> textFontWeight
    }

    val alignment = when (textAlign) {
        GdsHeadingAlignment.CenterAligned -> TextAlign.Center
        GdsHeadingAlignment.LeftAligned -> TextAlign.Start
        GdsHeadingAlignment.RightAligned -> TextAlign.End
    }

    Text(
        text = text,
        color = textColour,
        style = typography,
        fontWeight = fontWeight,
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = customContentDescription ?: text
                heading()
            },
        textAlign = alignment,
    )
}

internal sealed class GdsHeadingColour(val lightModeColour: Color, val darkModeColour: Color) {
    data object Default : GdsHeadingColour(light_theme_onBackground, dark_theme_onBackground)
    data class Custom(val customLightModeColour: Color, val customDarkModeColour: Color) :
        GdsHeadingColour(customLightModeColour, customDarkModeColour)
}

internal data class HeadingParameters(
    val text: String,
    val customContentDescription: String? = null,
    val style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
    val fontWeight: FontWeight? = null,
    val textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    val textColour: GdsHeadingColour = GdsHeadingColour.Default,
)

internal class HeadingParameterPreviewProvider : PreviewParameterProvider<HeadingParameters> {
    override val values: Sequence<HeadingParameters> = sequenceOf(
        HeadingParameters(text = "Large Title", style = GdsHeadingStyle.LargeTitle),
        HeadingParameters(text = "Title1", style = GdsHeadingStyle.Title1),
        HeadingParameters(text = "Title2", style = GdsHeadingStyle.Title2),
        HeadingParameters(text = "Title3", style = GdsHeadingStyle.Title3),
        HeadingParameters(text = "Body Bold", style = GdsHeadingStyle.Body),
        HeadingParameters(
            text = "Custom Style - Label Large",
            style = GdsHeadingStyle.Custom(
                Typography.labelLarge,
            ),
        ),
        HeadingParameters(
            text = "Title1 - Left Aligned",
            style = GdsHeadingStyle.Title1,
            textAlign = GdsHeadingAlignment.LeftAligned,
        ),
        HeadingParameters(
            text = "Title1 - Custom Color",
            style = GdsHeadingStyle.Title1,
            textColour = GdsHeadingColour.Custom(
                customLightModeColour = Color.Green,
                customDarkModeColour = Color.Red,
            ),
        ),
        HeadingParameters("Long Large Title - Lorem ipsum dolor sit amet, consectetur adipiscin"),
        HeadingParameters(
            text = "Title with custom content description",
            style = GdsHeadingStyle.LargeTitle,
            customContentDescription = "Custom content description",
        ),
    )
}

@OptIn(UnstableDesignSystemAPI::class)
@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Preview(showBackground = true)
@Composable
internal fun PreviewTitle() {
    val parameters = HeadingParameterPreviewProvider().values.toList()
    GdsTheme {
        Column {
            parameters.forEach {
                GdsHeading(
                    text = it.text,
                    customContentDescription = it.customContentDescription,
                    style = it.style,
                    textFontWeight = it.fontWeight,
                    textAlign = it.textAlign,
                    textColour = if (isSystemInDarkTheme()) {
                        it.textColour.darkModeColour
                    } else {
                        it.textColour.lightModeColour
                    },
                )
            }
        }
    }
}
