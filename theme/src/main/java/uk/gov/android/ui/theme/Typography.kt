package uk.gov.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeBody,
        fontWeight = FontWeight.Normal,
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_light)),
        fontSize = textSizeCallout,
        fontWeight = FontWeight.ExtraLight,
    ),
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH1,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH1,
    ),
    h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH2,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH2,
    ),
    h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH3,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH3,
    ),
    h4 = TextStyle(
        fontFamily = FontFamily(Font(R.font.gds_transport_bold)),
        fontSize = textSizeH4,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeightH4,
    ),
    button = TextStyle(
        fontFamily = FontFamily(
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
