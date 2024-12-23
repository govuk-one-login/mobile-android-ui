package uk.gov.android.ui.theme.m3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.gov.android.ui.theme.R
import uk.gov.android.ui.theme.buttonLineHeight
import uk.gov.android.ui.theme.buttonTextSize
import uk.gov.android.ui.theme.lineHeightH1
import uk.gov.android.ui.theme.lineHeightH2
import uk.gov.android.ui.theme.lineHeightH3
import uk.gov.android.ui.theme.lineHeightH4
import uk.gov.android.ui.theme.textSizeBody
import uk.gov.android.ui.theme.textSizeBody2
import uk.gov.android.ui.theme.textSizeH1
import uk.gov.android.ui.theme.textSizeH2
import uk.gov.android.ui.theme.textSizeH3
import uk.gov.android.ui.theme.textSizeH4

/**
 * [Material3 typography styles](https://m3.material.io/blog/migrating-material-3)
 *
 * Material3                    -> Material
 * textAppearanceDisplayLarge   -> textAppearanceDisplay2            No current GDS design
 * textAppearanceDisplayMedium  -> textAppearanceDisplay3            No current GDS design
 * textAppearanceDisplaySmall   -> textAppearanceHeadline1
 * textAppearanceHeadlineLarge  -> textAppearanceHeadline2
 * textAppearanceHeadlineMedium -> textAppearanceHeadline3
 * textAppearanceHeadlineSmall  -> textAppearanceHeadline4
 * textAppearanceTitleLarge     -> textAppearanceHeadline5           No current GDS design
 * textAppearanceTitleMedium    -> textAppearanceSubhead1/Subtitle1  No current GDS design
 * textAppearanceTitleSmall     -> textAppearanceSubhead2/Subtitle2  No current GDS design
 * textAppearanceBodyLarge      -> textAppearanceBody1
 * textAppearanceBodyMedium     -> textAppearanceBody2
 * textAppearanceBodySmall      -> textAppearanceCaption             No current GDS design
 * textAppearanceLabelLarge     -> textAppearanceButton
 * textAppearanceLabelMedium    -> textAppearanceOverline            No current GDS design
 * textAppearanceLabelSmall     -> N/A                               No current GDS design
 * */
val Typography = Typography(
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH1,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH1,
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH2,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH2,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH3,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH3,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH4,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH4,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeBody,
        fontWeight = FontWeight.Normal,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeBody2,
        fontWeight = FontWeight.ExtraLight,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = 17.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Light,
    ),
    labelLarge = TextStyle(
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
        lineHeight = buttonLineHeight,
    ),
)

@Preview
@Composable
private fun TypographyPreview() {
    val types: List<Pair<String, TextStyle>> = listOf(
        "displayLarge" to Typography.displayLarge,
        "displayMedium" to Typography.displayMedium,
        "displaySmall" to Typography.displaySmall,
        "headlineLarge" to Typography.headlineLarge,
        "headlineMedium" to Typography.headlineMedium,
        "headlineSmall" to Typography.headlineSmall,
        "titleLarge" to Typography.titleLarge,
        "titleMedium" to Typography.titleMedium,
        "titleSmall" to Typography.titleSmall,
        "bodyLarge" to Typography.bodyLarge,
        "bodyMedium" to Typography.bodyMedium,
        "bodySmall" to Typography.bodySmall,
        "labelLarge" to Typography.labelLarge,
        "labelMedium" to Typography.labelMedium,
        "labelSmall" to Typography.labelSmall,
    )
    GdsTheme {
        LazyColumn {
            items(types) { type ->
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(style = type.second, text = type.first)
                    Text(text = "  - ${type.second.fontSize.value.toInt()}sp")
                }
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
