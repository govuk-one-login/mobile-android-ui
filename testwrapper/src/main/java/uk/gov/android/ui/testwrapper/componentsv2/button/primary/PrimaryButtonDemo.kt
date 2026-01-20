package uk.gov.android.ui.testwrapper.componentsv2.button.primary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.buttonColors
import uk.gov.android.ui.theme.smallPadding

@Composable
fun PrimaryButtonDemo(
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
        // Primary Button with Icon
        GdsButton(
            text = stringResource(R.string.primary_button),
            buttonType = ButtonTypeV2.Icon(
                buttonColors = ButtonTypeV2.Primary().buttonColors(),
                contentDescription = stringResource(R.string.primary_button),
            ),
            onClick = {},
            enabled = true,
            contentModifier = Modifier.padding(),
            contentPosition = Arrangement.Absolute.Center,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                smallPadding,
            ),
        )

        // Disabled Primary button with Icon
        GdsButton(
            text = stringResource(R.string.primary_button),
            buttonType = ButtonTypeV2.Icon(
                buttonColors = ButtonTypeV2.Primary().buttonColors(),
                textStyle = ButtonTypeV2.Primary().textStyle.copy(
                    color = ButtonTypeV2.Primary().buttonColors().contentColor,
                ),
                contentDescription = stringResource(R.string.primary_button),
            ),
            onClick = { },
            enabled = false,
            contentModifier = Modifier.padding(),
            contentPosition = Arrangement.Absolute.Center,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                smallPadding,
            ),
        )
    }
}
