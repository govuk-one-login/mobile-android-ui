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
 * [Material3 typography styles](https://m3.material.io/blog/migrating-material-3)
 *
 * Material3                    -> GDS
 * 1. textAppearanceDisplayLarge   => No current GDS design
 * 2. textAppearanceDisplayMedium  => No current GDS design
 * 3. textAppearanceDisplaySmall   => Large Headline
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
        lineHeight = lineHeightB1,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeCallout,
        fontWeight = FontWeight.ExtraLight,
        lineHeight = lineHeightB2,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeSubheadline,
        fontWeight = FontWeight.Light,
        lineHeight = lineHeightB3,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily((Font(R.font.gds_transport_light))),
        fontSize = textSizeFootnote,
        fontWeight = FontWeight.Light,
        lineHeight = lineHeightB4,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily((Font(R.font.gds_transport_light))),
        fontSize = textSizeCaption,
        fontWeight = FontWeight.Light,
        lineHeight = lineHeightB5,
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
        lineHeight = lineHeightB1,
    ),
)

@Preview
@Composable
private fun TypographyPreview() {
    val types: List<Pair<String, TextStyle>> = listOf(
        "Large Title" to Typography.displayLarge,
        "displayMedium" to Typography.displayMedium,
        "displaySmall" to Typography.displaySmall,
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
