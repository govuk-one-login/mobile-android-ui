package uk.gov.android.ui.testwrapper.patterns.error

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreen
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenBodyContent
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenIcon
import uk.gov.android.ui.testwrapper.FullScreenBackHandler
import uk.gov.android.ui.theme.m3.Typography

@Composable
@Suppress("ComposeModifierMissing")
fun ErrorScreenDemo(
    isFullScreen: Boolean,
    displayTabRow: (Boolean) -> Unit = {},
) {
    if (isFullScreen) {
        FullScreenBackHandler(displayTabRow)
    }
    ErrorScreen(
        icon = { horizontalPadding ->
            GdsIcon(
                image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                contentDescription = stringResource(ErrorScreenIcon.ErrorIcon.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
                color = colorScheme.onBackground,
            )
        },
        title = { horizontalPadding ->
            GdsHeading(
                text = "This is an Error View title",
                modifier = Modifier
                    .padding(horizontal = horizontalPadding),
                textAlign = GdsHeadingAlignment.CenterAligned,
            )
        },
        body = { horizontalPadding ->
            toBodyContent(
                persistentListOf(
                    ErrorScreenBodyContent.Text("Body single line (regular)"),
                    ErrorScreenBodyContent.Text("Body single line (bold)", true),
                ),
                horizontalPadding,
            )
        },
        primaryButton = {
            GdsButton(
                text = "Primary button",
                buttonType = ButtonTypeV2.Primary(),
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}

internal fun LazyListScope.toBodyContent(
    body: ImmutableList<ErrorScreenBodyContent.Text>? = null,
    horizontalItemPadding: Dp,
) {
    val itemPadding = PaddingValues(horizontal = horizontalItemPadding)
    body?.forEach { item ->
        item {
            val textStyle = if (item.useBoldStyle) {
                Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            } else {
                Typography.bodyLarge
            }
            Text(
                text = item.bodyText,
                style = textStyle,
                color = colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(itemPadding),
            )
        }
    }
}
