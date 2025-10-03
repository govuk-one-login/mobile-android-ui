package uk.gov.android.ui.testwrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.dialogue.DialogueButtonParameters
import uk.gov.android.ui.componentsv2.dialogue.GdsDialogue
import uk.gov.android.ui.testwrapper.componentsv2.status.StatusOverlayDemo
import uk.gov.android.ui.testwrapper.componentsv2.topappbar.GdsTopAppBarDemo
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.spacingDouble

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val displayTopAppBarDemo: MutableState<Boolean> = remember {
                mutableStateOf(false)
            }
            var displayDialogue by remember { mutableStateOf(false) }
            GdsTheme {
                StatusOverlayDemo {
                    Column {
                        GdsButton(
                            text = stringResource(R.string.display_top_app_bar_demo),
                            buttonType = ButtonTypeV2.Primary(),
                            onClick = {
                                displayTopAppBarDemo.value = !displayTopAppBarDemo.value
                            }
                        )
                        Spacer(modifier = Modifier.height(spacingDouble))
                        GdsButton(
                            text = LAUNCH_DIALOGUE_BUTTON,
                            buttonType = ButtonTypeV2.Primary(),
                            onClick = {
                                displayDialogue = !displayDialogue
                            }
                        )
                    }
                }
                if (displayTopAppBarDemo.value) {
                    GdsTopAppBarDemo { displayTopAppBarDemo.value = false }
                }
                if (displayDialogue) {
                    GdsDialogue(
                        headingText = DIALOGUE_TITLE,
                        contentText = R.string.dialogue_demo_content,
                        changeContentBackground = true,
                        buttonParameters = persistentListOf(
                            DialogueButtonParameters(
                                buttonType = ButtonTypeV2.Secondary(),
                                text = DIALOGUE_BUTTON,
                                onClick = { displayDialogue = !displayDialogue },
                            ),
                        ),
                    ) {
                        displayDialogue = !displayDialogue
                    }
                }
            }
        }
    }

    companion object {
        private const val LAUNCH_DIALOGUE_BUTTON = "Display Dialogue"
        private const val DIALOGUE_TITLE = "Dialogue component"
        private const val DIALOGUE_BUTTON = "Dismiss"
    }
}
