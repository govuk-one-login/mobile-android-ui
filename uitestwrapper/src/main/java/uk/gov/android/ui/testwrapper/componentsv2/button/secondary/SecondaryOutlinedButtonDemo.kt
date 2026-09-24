package uk.gov.android.ui.testwrapper.componentsv2.button.secondary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonIcon
import uk.gov.android.ui.componentsv2.button.ButtonIconPosition
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.theme.smallPadding

@Composable
fun SecondaryOutlinedButtonDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = smallPadding),
        verticalArrangement = Arrangement.spacedBy(smallPadding),
    ) {
        GdsButton(
            text = stringResource(R.string.secondary_button),
            buttonType = ButtonTypeV2.SecondaryOutlined(),
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
        GdsButton(
            text = stringResource(R.string.secondary_button),
            buttonType = ButtonTypeV2.SecondaryOutlined(),
            onClick = {},
            icon = ButtonIcon.opensInWebBrowser(),
            modifier = Modifier.fillMaxWidth(),
        )
        GdsButton(
            text = stringResource(R.string.secondary_button),
            buttonType = ButtonTypeV2.SecondaryOutlined(),
            onClick = {},
            icon = ButtonIcon(
                icon = ImageVector.vectorResource(R.drawable.ic_external_site),
                contentDescription = stringResource(R.string.opens_in_external_browser),
                position = ButtonIconPosition.Leading,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
        GdsButton(
            text = stringResource(R.string.secondary_button),
            buttonType = ButtonTypeV2.SecondaryOutlined(),
            onClick = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
