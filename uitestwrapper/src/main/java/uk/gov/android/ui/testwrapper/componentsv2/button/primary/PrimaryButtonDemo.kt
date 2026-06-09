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

private const val VISIBLE_DELAY = 2000L

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
        var isIconLoading by remember { mutableStateOf(false) }
        var isPrimaryLoading by remember { mutableStateOf(false) }

        LaunchedEffect(isIconLoading) {
            if (isIconLoading) {
                delay(VISIBLE_DELAY)
                isIconLoading = false
            }
        }

        LaunchedEffect(isPrimaryLoading) {
            if (isPrimaryLoading) {
                delay(VISIBLE_DELAY)
                isPrimaryLoading = false
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
            onClick = { isIconLoading = true },
            enabled = true,
            loading = isIconLoading,
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
            onClick = { isPrimaryLoading = true },
            enabled = true,
            loading = isPrimaryLoading,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
