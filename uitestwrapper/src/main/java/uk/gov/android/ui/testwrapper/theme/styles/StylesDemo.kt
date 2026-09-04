package uk.gov.android.ui.testwrapper.theme.styles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.gov.android.ui.theme.largePadding
import uk.gov.android.ui.theme.m3.ExtraTypographyPreviewParams
import uk.gov.android.ui.theme.m3.TypographyPreviewParams

@Composable
fun StylesDemo(modifier: Modifier = Modifier) {
    val styles = TypographyPreviewParams.types.plus(ExtraTypographyPreviewParams.types)

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        styles.forEach { (name, style) ->
            Text(
                text = name,
                style = style,
                modifier = Modifier.padding(largePadding),
            )
        }
    }
}
