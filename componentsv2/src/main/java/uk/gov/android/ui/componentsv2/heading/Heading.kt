package uk.gov.android.ui.componentsv2.heading

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

enum class HeadingStyle {
    LargeTitle, // 34 Large title / bold
    Title1,     // 28 Title 1 / bold
    Title2,     // 22 Title 2 / bold
    Title3,     // 20 Title 3 / bold
    BodyBold    // 17 Body / bold
}

@Composable
fun Heading(
    text: String,
    modifier: Modifier = Modifier,
    style: HeadingStyle = HeadingStyle.LargeTitle,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    val typography = when (style) {
        HeadingStyle.LargeTitle -> MaterialTheme.typography.headlineLarge
        HeadingStyle.Title1 -> MaterialTheme.typography.headlineMedium
        HeadingStyle.Title2 -> MaterialTheme.typography.headlineSmall
        HeadingStyle.Title3 -> MaterialTheme.typography.titleLarge
        HeadingStyle.BodyBold -> MaterialTheme.typography.bodyLarge
    }

    Text(
        text = text,
        style = typography.copy(fontWeight = FontWeight.Bold),
        textAlign = textAlign,
        color = color,
        modifier = modifier
    )
}