package uk.gov.android.ui.testwrapper.componentsv2.button.secondary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonIcon
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.theme.smallPadding

@Suppress("LongMethod")
@Composable
fun SecondaryButtonDemo(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(smallPadding)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        // Enabled Secondary button with preset icon
        GdsButton(
            text = stringResource(R.string.secondary_button),
            icon = ButtonIcon.opensInWebBrowser(),
            buttonType = ButtonTypeV2.Secondary(),
            onClick = {},
            enabled = true,
            contentModifier = Modifier.padding(),
            contentPosition = Arrangement.Absolute.Center,
            textAlign = TextAlign.Left,
        )

        // Disabled Secondary button with preset icon
        GdsButton(
            text = stringResource(R.string.secondary_button),
            icon = ButtonIcon.opensInWebBrowser(),
            buttonType = ButtonTypeV2.Secondary(),
            onClick = {},
            enabled = false,
            contentModifier = Modifier.padding(),
            contentPosition = Arrangement.Absolute.Center,
            textAlign = TextAlign.Left,
        )

        // Enabled Secondary button with error icon
        GdsButton(
            text = stringResource(R.string.secondary_button),
            icon = ButtonIcon(
                icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
                contentDescription = stringResource(R.string.secondary_button),
            ),
            buttonType = ButtonTypeV2.Secondary(),
            onClick = {},
            enabled = true,
            contentModifier = Modifier.padding(),
            contentPosition = Arrangement.Absolute.Center,
            textAlign = TextAlign.Left,
        )

        // Disabled Secondary button with error icon
        GdsButton(
            text = stringResource(R.string.secondary_button),
            icon = ButtonIcon(
                icon = ImageVector.vectorResource(R.drawable.ic_error_filled),
                contentDescription = stringResource(R.string.secondary_button),
            ),
            buttonType = ButtonTypeV2.Secondary(),
            onClick = {},
            enabled = false,
            contentModifier = Modifier.padding(),
            contentPosition = Arrangement.Absolute.Center,
            textAlign = TextAlign.Left,
        )
    }
}
