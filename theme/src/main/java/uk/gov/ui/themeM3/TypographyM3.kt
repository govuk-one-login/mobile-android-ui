package uk.gov.ui.themeM3

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import uk.gov.ui.theme.R
import uk.gov.ui.theme.buttonLineHeight
import uk.gov.ui.theme.buttonTextSize
import uk.gov.ui.theme.lineHeightH1
import uk.gov.ui.theme.lineHeightH2
import uk.gov.ui.theme.lineHeightH3
import uk.gov.ui.theme.lineHeightH4
import uk.gov.ui.theme.textSizeBody
import uk.gov.ui.theme.textSizeBody2
import uk.gov.ui.theme.textSizeH1
import uk.gov.ui.theme.textSizeH2
import uk.gov.ui.theme.textSizeH3
import uk.gov.ui.theme.textSizeH4

// Set of Material typography styles to start with
val TypographyM3 = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeBody,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeBody2,
        fontWeight = FontWeight.ExtraLight
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH1,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH1
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH2,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH2
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH3,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH3
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH4,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH4
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(
            Font(
                resId = R.font.gds_transport_light,
                weight = FontWeight.Normal
            ),
            Font(
                resId = R.font.gds_transport_bold,
                weight = FontWeight.Bold
            )
        ),
        fontSize = buttonTextSize,
        lineHeight = buttonLineHeight
    )
)
