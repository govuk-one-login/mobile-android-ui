package uk.gov.android.ui.componentsv2.heading

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.light_theme_background
import uk.gov.android.ui.theme.m3.light_theme_onBackground
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
}

enum class GdsHeadingColour(val lightModeColor: Color, val darkModeColor: Color) {
    Default(light_theme_onBackground, light_theme_background),
    Custom(Color.Unspecified, Color.Unspecified),
}

@UnstableDesignSystemAPI
@Composable
fun GdsHeading(
    text: String,
    modifier: Modifier = Modifier,
    style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
    fontWeight: FontWeight? = null,
    textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    textColour: GdsHeadingColour = GdsHeadingColour.Default,
    horizontalPadding: Dp = 16.dp,
) {
    val heading = stringResource(R.string.heading, text)

    val typography = when (style) {
        GdsHeadingStyle.LargeTitle -> Typography.displaySmall
        GdsHeadingStyle.Title1 -> Typography.headlineLarge
        GdsHeadingStyle.Title2 -> Typography.headlineMedium
        GdsHeadingStyle.Title3 -> Typography.headlineSmall
        GdsHeadingStyle.BodyBold -> Typography.bodyLarge
    }

    val alignment = when (textAlign) {
        GdsHeadingAlignment.CenterAligned -> TextAlign.Center
        GdsHeadingAlignment.LeftAligned -> TextAlign.Start
    }

    val isDark = isSystemInDarkTheme()
    val colour = if (textColour == GdsHeadingColour.Default) {
        if (isDark) GdsHeadingColour.Default.darkModeColor else GdsHeadingColour.Default.lightModeColor
    } else {
        if (isDark) textColour.darkModeColor else textColour.lightModeColor
    }

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

internal data class HeadingParameters(
    val text: String,
    val style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
    val fontWeight: FontWeight? = null,
    val textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    val textColour: GdsHeadingColour = GdsHeadingColour.Default,
)

internal class HeadingParameterPreviewProvider : PreviewParameterProvider<HeadingParameters> {
    override val values: Sequence<HeadingParameters> = sequenceOf(
        HeadingParameters("Large Title - center aligned", style = GdsHeadingStyle.LargeTitle),
        HeadingParameters("Title1", style = GdsHeadingStyle.Title1),
        HeadingParameters("Title2", style = GdsHeadingStyle.Title2),
        HeadingParameters("Title3", style = GdsHeadingStyle.Title3),
        HeadingParameters("Body Bold", style = GdsHeadingStyle.BodyBold),
        HeadingParameters("Title1", style = GdsHeadingStyle.Title1, textAlign = GdsHeadingAlignment.LeftAligned),
        HeadingParameters("Title1", style = GdsHeadingStyle.Title1, textColour = GdsHeadingColour.Custom),
        HeadingParameters("Long Large Title - Lorem ipsum dolor sit amet, consectetur adipiscin"),
        HeadingParameters("Subtitle", style = GdsHeadingStyle.LargeTitle, fontWeight = FontWeight.W700),
    )
}

@OptIn(UnstableDesignSystemAPI::class)
@PreviewLightDark
@Composable
private fun PreviewTitle(
    @PreviewParameter(HeadingParameterPreviewProvider::class)
    parameters: HeadingParameters,
) {
    GdsTheme {
        GdsHeading(
            text = parameters.text,
            style = parameters.style,
            fontWeight = parameters.fontWeight,
            textAlign = parameters.textAlign,
        )
    }
}
