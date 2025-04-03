package uk.gov.android.ui.componentsv2.heading

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.dark_theme_onBackground
import uk.gov.android.ui.theme.m3.light_theme_onBackground
import uk.gov.android.ui.theme.meta.ExcludeFromJacocoGeneratedReport
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

enum class GdsHeadingStyle {
    LargeTitle,
    Title1,
    Title2,
    Title3,
    BodyBold,
}

enum class GdsHeadingAlignment {
    CenterAligned,
    LeftAligned,
    RightAligned,
}

sealed class GdsHeadingColour(val lightModeColor: Color, val darkModeColor: Color) {
    object Default : GdsHeadingColour(light_theme_onBackground, dark_theme_onBackground)
    data class Custom(val customLightModeColor: Color, val customDarkModeColor: Color) :
        GdsHeadingColour(customLightModeColor, customDarkModeColor)
}

@UnstableDesignSystemAPI
@Composable
fun GdsHeading(
    text: String,
    modifier: Modifier = Modifier,
    style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
    textFontWeight: FontWeight? = null,
    textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    textColour: GdsHeadingColour = GdsHeadingColour.Default,
    horizontalPadding: Dp = smallPadding,
) {
    val heading = stringResource(R.string.heading, text)

    val typography = when (style) {
        GdsHeadingStyle.LargeTitle -> Typography.displaySmall
        GdsHeadingStyle.Title1 -> Typography.headlineLarge
        GdsHeadingStyle.Title2 -> Typography.headlineMedium
        GdsHeadingStyle.Title3 -> Typography.headlineSmall
        GdsHeadingStyle.BodyBold -> Typography.bodyLarge
    }

    val fontWeight = when (style) {
        GdsHeadingStyle.BodyBold -> FontWeight.Bold
        else -> textFontWeight
    }

    val alignment = when (textAlign) {
        GdsHeadingAlignment.CenterAligned -> TextAlign.Center
        GdsHeadingAlignment.LeftAligned -> TextAlign.Start
        GdsHeadingAlignment.RightAligned -> TextAlign.End
    }

    val colour = if (isSystemInDarkTheme()) textColour.darkModeColor else textColour.lightModeColor

    Text(
        text = text,
        color = colour,
        style = typography,
        fontWeight = fontWeight,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding)
            .semantics {
                contentDescription = heading
                heading()
            },
        textAlign = alignment,
    )
}

data class HeadingParameters(
    val text: String,
    val style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
    val fontWeight: FontWeight? = null,
    val textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    val textColour: GdsHeadingColour = GdsHeadingColour.Default,
)

class HeadingParameterPreviewProvider : PreviewParameterProvider<HeadingParameters> {
    override val values: Sequence<HeadingParameters> = sequenceOf(
        HeadingParameters(text = "Large Title", style = GdsHeadingStyle.LargeTitle),
        HeadingParameters(text = "Title1", style = GdsHeadingStyle.Title1),
        HeadingParameters(text = "Title2", style = GdsHeadingStyle.Title2),
        HeadingParameters(text = "Title3", style = GdsHeadingStyle.Title3),
        HeadingParameters(text = "Body Bold", style = GdsHeadingStyle.BodyBold),
        HeadingParameters(
            text = "Title1 - Left Aligned",
            style = GdsHeadingStyle.Title1,
            textAlign = GdsHeadingAlignment.LeftAligned,
        ),
        HeadingParameters(
            text = "Title1 - Custom Color",
            style = GdsHeadingStyle.Title1,
            textColour = GdsHeadingColour.Custom(Color.Green, Color.Red),
        ),
        HeadingParameters("Long Large Title - Lorem ipsum dolor sit amet, consectetur adipiscin"),
    )
}

@OptIn(UnstableDesignSystemAPI::class)
@ExcludeFromJacocoGeneratedReport
@PreviewLightDark
@Composable
internal fun PreviewTitle() {
    val parameters = HeadingParameterPreviewProvider().values.toList()
    GdsTheme {
        Column {
            parameters.forEach {
                GdsHeading(
                    text = it.text,
                    style = it.style,
                    textFontWeight = it.fontWeight,
                    textAlign = it.textAlign,
                    textColour = it.textColour,
                )
            }
        }
    }
}
