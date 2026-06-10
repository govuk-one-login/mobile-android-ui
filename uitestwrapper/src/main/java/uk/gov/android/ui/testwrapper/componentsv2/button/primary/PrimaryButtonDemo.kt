package uk.gov.android.ui.testwrapper.componentsv2.button.primary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.buttonColors
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.smallPadding

private const val BUTTON_LOADING_DURATION = 2000L // Milliseconds

@Composable
fun PrimaryButtonDemo(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(smallPadding),
        verticalArrangement = Arrangement.spacedBy(smallPadding),
    ) {
        var isIconButtonLoading by remember { mutableStateOf(false) }
        var isPrimaryButtonLoading by remember { mutableStateOf(false) }

        LaunchedEffect(isIconButtonLoading) {
            if (isIconButtonLoading) {
                delay(BUTTON_LOADING_DURATION)
                isIconButtonLoading = false
            }
        }

        LaunchedEffect(isPrimaryButtonLoading) {
            if (isPrimaryButtonLoading) {
                delay(BUTTON_LOADING_DURATION)
                isPrimaryButtonLoading = false
            }
        }

        // Primary Button with Icon
        GdsButton(
            text = stringResource(R.string.primary_button),
            buttonType = ButtonTypeV2.Icon(
                buttonColors = ButtonTypeV2.Primary().buttonColors(),
                contentDescription = stringResource(R.string.primary_button),
                textStyle = ExtraTypography.bodyLargeBold,
            ),
            onClick = { isIconButtonLoading = true },
            enabled = true,
            loading = isIconButtonLoading,
            modifier = Modifier.fillMaxWidth(),
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
            modifier = Modifier.fillMaxWidth(),
        )

        // Primary Button
        GdsButton(
            text = stringResource(R.string.primary_button),
            buttonType = ButtonTypeV2.Primary(),
            onClick = { isPrimaryButtonLoading = true },
            enabled = true,
            loading = isPrimaryButtonLoading,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
