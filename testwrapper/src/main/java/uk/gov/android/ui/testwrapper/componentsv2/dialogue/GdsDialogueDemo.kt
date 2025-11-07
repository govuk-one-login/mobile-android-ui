package uk.gov.android.ui.testwrapper.componentsv2.dialogue

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.dialogue.DialogueButtonParameters
import uk.gov.android.ui.componentsv2.dialogue.GdsDialogue
import uk.gov.android.ui.componentsv2.status.GdsStatusOverlay
import uk.gov.android.ui.testwrapper.R
import uk.gov.android.ui.theme.spacingDouble

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GdsDialogueDemo() {
    var displayDialogue by remember { mutableStateOf(false) }
    val statusOverlayState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            GdsStatusOverlay(
                hostState = statusOverlayState,
                modifier = Modifier.padding(horizontal = spacingDouble),
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GdsButton(
                text = stringResource(R.string.dialogue_demo_button_launch),
                buttonType = ButtonTypeV2.Primary(),
                onClick = {
                    displayDialogue = !displayDialogue
                },
            )
            if (displayDialogue) {
                GdsDialogue(
                    headingText = stringResource(R.string.dialogue_demo_title),
                    contentText = stringResource(R.string.dialogue_demo_content),
                    buttonParameters =
                    persistentListOf(
                        DialogueButtonParameters(
                            buttonType = ButtonTypeV2.Secondary(),
                            text = stringResource(R.string.dialogue_demo_button_dismiss),
                            onClick = {
                                displayDialogue = !displayDialogue
                                scope.launch {
                                    statusOverlayState.showSnackbar("Dismiss button tapped")
                                }
                            },
                        ),
                        DialogueButtonParameters(
                            buttonType = ButtonTypeV2.Primary(),
                            text = stringResource(R.string.dialogue_demo_button_else),
                            onClick = {
                                displayDialogue = !displayDialogue
                                scope.launch {
                                    statusOverlayState.showSnackbar("Something else button tapped")
                                }
                            },
                        ),
                    ),
                ) {
                    displayDialogue = !displayDialogue
                }
            }
        }
    }
}
