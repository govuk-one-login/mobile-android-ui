package uk.gov.android.ui.testwrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.dialogue.DialogueButtonParameters
import uk.gov.android.ui.componentsv2.dialogue.GdsDialogue
import uk.gov.android.ui.testwrapper.componentsv2.camera.CameraContentDemo
import uk.gov.android.ui.testwrapper.componentsv2.status.StatusOverlayDemo
import uk.gov.android.ui.testwrapper.componentsv2.topappbar.GdsTopAppBarDemo
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val displayTopAppBarDemo: MutableState<Boolean> = remember {
                mutableStateOf(false)
            }
            var displayDialogue by remember { mutableStateOf(false) }
            var displayQrDialog by remember { mutableStateOf(false) }
            var menuTitleToDisplay by remember { mutableStateOf("") }
            GdsTheme {
                StatusOverlayDemo(menuTitleToDisplay = menuTitleToDisplay) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(spacingDouble)
                    ) {
                        GdsButton(
                            text = stringResource(R.string.display_top_app_bar_demo),
                            buttonType = ButtonTypeV2.Primary(),
                            onClick = {
                                displayTopAppBarDemo.value = !displayTopAppBarDemo.value
                            }
                        )
                        GdsButton(
                            text = stringResource(R.string.dialogue_demo_button_launch),
                            buttonType = ButtonTypeV2.Primary(),
                            onClick = {
                                displayDialogue = !displayDialogue
                            }
                        )
                        GdsButton(
                            text = stringResource(R.string.dialogue_demo_barcode_button_launch),
                            buttonType = ButtonTypeV2.Primary(),
                            onClick = {
                                displayQrDialog = !displayQrDialog
                            }
                        )
                    }
                }
                if (displayTopAppBarDemo.value) {
                    GdsTopAppBarDemo(
                        dismiss = { displayTopAppBarDemo.value = false },
                        onMenuSelect = { selectedMenuTitle ->
                            menuTitleToDisplay = selectedMenuTitle
                        }
                    )
                }
                if (displayDialogue) {
                    GdsDialogue(
                        headingText = stringResource(R.string.dialogue_demo_title),
                        contentText = stringResource(R.string.dialogue_demo_content),
                        buttonParameters = persistentListOf(
                            DialogueButtonParameters(
                                buttonType = ButtonTypeV2.Secondary(),
                                text = stringResource(R.string.dialogue_demo_button_dismiss),
                                onClick = { displayDialogue = !displayDialogue },
                            ),
                            DialogueButtonParameters(
                                buttonType = ButtonTypeV2.Primary(),
                                text = stringResource(R.string.dialogue_demo_button_else),
                                onClick = { displayDialogue = !displayDialogue },
                            ),
                        ),
                    ) {
                        displayDialogue = !displayDialogue
                    }
                }
                if (displayQrDialog) {
                    CameraContentDemo(
                        onDismiss = { displayQrDialog = false }
                    )
                }
            }
        }
    }
}
