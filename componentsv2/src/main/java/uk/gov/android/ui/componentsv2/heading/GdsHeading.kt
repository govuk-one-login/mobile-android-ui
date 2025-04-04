package uk.gov.android.ui.componentsv2.heading

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
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

sealed class GdsHeadingColour(val lightModeColour: Color, val darkModeColour: Color) {
    data object Default : GdsHeadingColour(light_theme_onBackground, dark_theme_onBackground)
    data class Custom(val customLightModeColour: Color, val customDarkModeColour: Color) :
        GdsHeadingColour(customLightModeColour, customDarkModeColour)
}

@UnstableDesignSystemAPI
@Composable
fun GdsHeading(
    style: GdsHeadingStyle = GdsHeadingStyle.LargeTitle,
    textAlign: GdsHeadingAlignment = GdsHeadingAlignment.CenterAligned,
    textColour: GdsHeadingColour = GdsHeadingColour.Default,
    text: String,
    modifier: Modifier = Modifier,
    textFontWeight: FontWeight? = null,
) {
    val heading = stringResource(R.string.heading, text)

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

    val colour = if (isSystemInDarkTheme()) textColour.darkModeColour else textColour.lightModeColour

    Text(
        text = text,
        color = colour,
        style = typography,
        fontWeight = fontWeight,
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = heading
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
            textColour = GdsHeadingColour.Custom(Color.Green, Color.Red),
        ),
        HeadingParameters("Long Large Title - Lorem ipsum dolor sit amet, consectetur adipiscin"),
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
                    style = it.style,
                    textFontWeight = it.fontWeight,
                    textAlign = it.textAlign,
                    textColour = it.textColour,
                )
            }
        }
    }
}
