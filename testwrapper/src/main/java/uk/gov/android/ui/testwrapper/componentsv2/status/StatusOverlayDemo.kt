package uk.gov.android.ui.testwrapper.componentsv2.status

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.status.GdsStatusOverlay
import uk.gov.android.ui.theme.largePadding
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StatusOverlayDemo() {
    val statusOverlayState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            GdsStatusOverlay(
                hostState = statusOverlayState,
                modifier = Modifier.padding(horizontal = spacingDouble),
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(smallPadding)
                    .navigationBarsPadding()
                    .statusBarsPadding()
            ) {
                GdsButton(
                    text = "Display overlay",
                    buttonType = ButtonTypeV2.Primary(),
                    onClick = {
                        scope.launch {
                            statusOverlayState.showSnackbar("This is a message")
                        }
                    },
                    modifier = Modifier.padding(bottom = smallPadding)
                )
                Spacer(modifier = Modifier.height(largePadding))
            }
        }
    }
}
