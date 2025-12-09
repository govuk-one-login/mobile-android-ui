package uk.gov.android.ui.theme.m3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.R
import uk.gov.android.ui.theme.buttonTextSize
import uk.gov.android.ui.theme.lineHeightB1
import uk.gov.android.ui.theme.lineHeightB2
import uk.gov.android.ui.theme.lineHeightB3
import uk.gov.android.ui.theme.lineHeightB4
import uk.gov.android.ui.theme.lineHeightB5
import uk.gov.android.ui.theme.lineHeightH1
import uk.gov.android.ui.theme.lineHeightH2
import uk.gov.android.ui.theme.lineHeightH3
import uk.gov.android.ui.theme.lineHeightH4
import uk.gov.android.ui.theme.textSizeBody
import uk.gov.android.ui.theme.textSizeCallout
import uk.gov.android.ui.theme.textSizeCaption
import uk.gov.android.ui.theme.textSizeFootnote
import uk.gov.android.ui.theme.textSizeH1
import uk.gov.android.ui.theme.textSizeH2
import uk.gov.android.ui.theme.textSizeH3
import uk.gov.android.ui.theme.textSizeH4
import uk.gov.android.ui.theme.textSizeSubheadline

/**
 * Default text style that matches how Figma positions text.
 *
 * https://medium.com/androiddevelopers/fixing-font-padding-in-compose-text-768cd232425b
 * https://dev.to/canyudev/android-and-figma-typography-and-how-to-achieve-100-fidelity-l40
 */
private val DefaultTextStyle = TextStyle.Default.copy(
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None,
    ),
)

/**
 * [Material3 typography styles](https://m3.material.io/blog/migrating-material-3)
 *
 * Material3                    -> GDS
 * 1. textAppearanceDisplayLarge   => No current GDS design
 * 2. textAppearanceDisplayMedium  => No current GDS design
 * 3. textAppearanceDisplaySmall   => Large Title
 * 4. textAppearanceHeadlineLarge  => Title 1
 * 5. textAppearanceHeadlineMedium => Title 2
 * 6. textAppearanceHeadlineSmall  => Title 3
 * 7. textAppearanceTitleLarge     => No current GDS design
 * 8. textAppearanceTitleMedium    => No current GDS design
 * 9. textAppearanceTitleSmall     => No current GDS design
 * 10. textAppearanceBodyLarge     => Body
 * 11. textAppearanceBodyMedium    => Callout
 * 12. textAppearanceBodySmall     => Subheadline
 * 13. textAppearanceLabelLarge    => Button text
 * 14. textAppearanceLabelMedium   => Footnote
 * 15. textAppearanceLabelSmall    => Caption
 * */
val Typography = Typography(
    displaySmall = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH1,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH1,
    ),
    headlineLarge = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH2,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH2,
    ),
    headlineMedium = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH3,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH3,
    ),
    headlineSmall = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH4,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH4,
    ),
    bodyLarge = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeBody,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB1,
    ),
    bodyMedium = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeCallout,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB2,
    ),
    bodySmall = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeSubheadline,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB3,
    ),
    labelMedium = DefaultTextStyle.copy(
        fontFamily = FontFamily((Font(R.font.gds_transport_light))),
        fontSize = textSizeFootnote,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB4,
    ),
    labelSmall = DefaultTextStyle.copy(
        fontFamily = FontFamily((Font(R.font.gds_transport_light))),
        fontSize = textSizeCaption,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB5,
    ),
    labelLarge = DefaultTextStyle.copy(
        fontFamily = FontFamily(
            Font(
                resId = R.font.gds_transport_light,
                weight = FontWeight.Light,
            ),
            Font(
                resId = R.font.gds_transport_light,
                weight = FontWeight.Normal,
            ),
            Font(
                resId = R.font.gds_transport_bold,
                weight = FontWeight.Bold,
            ),
        ),
        fontSize = buttonTextSize,
        lineHeight = lineHeightB1,
    ),
)

/**
 * Supplementary class to contain text styles not defined in Typography
 *
 * displaySmallReg   => Large Title light font family (ff) / normal font weight (fw)
 * headlineLargeReg  => Title 1 light ff / normal fw
 * headlineMediumReg => Title 2 light ff / normal fw
 * headlineSmallReg => Title 3 light ff / normal fw
 * bodyMediumBold => Callout bold ff / normal fw
 * bodySmallBold => Subheadline bold ff / normal fw
 * labelMediumBold => Footnote bold ff / normal fw
 * labelSmallBold => Caption bold ff / normal fw
 * bodyLargeBold => Body bold ff / normal fw
 */
val ExtraTypography = GdsTypography(
    headlineSmallMono = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.roboto_mono_variable_font_wght)),
        fontSize = textSizeH4,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH4,
    ),
    bodyLargeMono = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.roboto_mono_variable_font_wght)),
        fontSize = textSizeBody,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightB1,
    ),
    displaySmallReg = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeH1,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH1,
    ),
    headlineLargeReg = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeH2,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH2,
    ),
    headlineMediumReg = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeH3,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH3,
    ),
    headlineSmallReg = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeH4,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightH4,
    ),
    bodyMediumBold = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeCallout,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB2,
    ),
    bodySmallBold = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeSubheadline,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB3,
    ),
    labelMediumBold = DefaultTextStyle.copy(
        fontFamily = FontFamily((Font(R.font.gds_transport_bold))),
        fontSize = textSizeFootnote,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB4,
    ),
    labelSmallBold = DefaultTextStyle.copy(
        fontFamily = FontFamily((Font(R.font.gds_transport_bold))),
        fontSize = textSizeCaption,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB5,
    ),
    bodyLargeBold = DefaultTextStyle.copy(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeBody,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeightB1,
    ),
)

object TypographyPreviewParams {
    val types: List<Pair<String, TextStyle>> = listOf(
        "Large Title" to Typography.displayLarge,
        "displayMedium" to Typography.displayMedium,
        "Large Headline" to Typography.displaySmall,
        "Title 1" to Typography.headlineLarge,
        "Title 2" to Typography.headlineMedium,
        "Title 3" to Typography.headlineSmall,
        "titleLarge" to Typography.titleLarge,
        "titleMedium" to Typography.titleMedium,
        "titleSmall" to Typography.titleSmall,
        "Body" to Typography.bodyLarge,
        "Callout" to Typography.bodyMedium,
        "Subheadline" to Typography.bodySmall,
        "Button text" to Typography.labelLarge,
        "Footnote" to Typography.labelMedium,
        "Caption" to Typography.labelSmall,
    )
}

object ExtraTypographyPreviewParams {
    val types: List<Pair<String, TextStyle>> = listOf(
        "Large Headline Regular" to ExtraTypography.displaySmallReg,
        "Title 1 Regular" to ExtraTypography.headlineLargeReg,
        "Title 2 Regular" to ExtraTypography.headlineMediumReg,
        "Title 3 Regular" to ExtraTypography.headlineSmallReg,
        "Body Bold" to ExtraTypography.bodyLargeBold,
        "Callout Bold" to ExtraTypography.bodyMediumBold,
        "Subheadline Bold" to ExtraTypography.bodySmallBold,
        "Footnote Bold" to ExtraTypography.labelMediumBold,
        "Caption Bold" to ExtraTypography.labelSmallBold,
    )
}

@Preview
@Composable
internal fun TypographyPreview() {
    GdsTheme {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            TypographyPreviewParams.types.forEach { type ->
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        style = type.second,
                        text = type.first,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "  - ${type.second.fontSize.value.toInt()}sp",
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview
@Composable
internal fun ExtraTypographyPreview() {
    GdsTheme {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            ExtraTypographyPreviewParams.types.forEach { type ->
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        style = type.second,
                        text = type.first,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "  - ${type.second.fontSize.value.toInt()}sp",
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

/**
 * Supplementary class to contain text styles not defined in Typography
 *
 * @param headlineSmallMono TextStyle headlineSmall style with roboto mono font family
 * @param bodyLargeMono TextStyle bodyLarge style with roboto mono font family
 */
data class GdsTypography(
    val headlineSmallMono: TextStyle,
    val bodyLargeMono: TextStyle,
    val displaySmallReg: TextStyle,
    val headlineLargeReg: TextStyle,
    val headlineMediumReg: TextStyle,
    val headlineSmallReg: TextStyle,
    val bodyMediumBold: TextStyle,
    val bodySmallBold: TextStyle,
    val labelMediumBold: TextStyle,
    val labelSmallBold: TextStyle,
    val bodyLargeBold: TextStyle,
)
