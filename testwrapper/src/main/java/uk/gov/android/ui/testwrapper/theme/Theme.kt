package uk.gov.android.ui.testwrapper.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.heading.GdsHeadingStyle
import uk.gov.android.ui.testwrapper.DetailItem
import uk.gov.android.ui.testwrapper.theme.styles.StylesDemo
import uk.gov.android.ui.theme.smallPadding

@Composable
fun Theme(
    modifier: Modifier = Modifier,
    onNavigate: (Any) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(ThemeDestination.entries()) { destination ->
            GdsHeading(
                text = destination.label,
                modifier =
                Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onNavigate(destination)
                    })
                    .padding(smallPadding),
                textAlign = GdsHeadingAlignment.LeftAligned,
                style = GdsHeadingStyle.Title3,
            )
            HorizontalDivider(color = Color.Black)
        }
    }
}

@Composable
fun ThemeDetail(
    detailItem: DetailItem,
) {
    when (detailItem.label) {
        STYLES_SCREEN -> StylesDemo()
    }
}

const val STYLES_SCREEN = "stylesScreen"
