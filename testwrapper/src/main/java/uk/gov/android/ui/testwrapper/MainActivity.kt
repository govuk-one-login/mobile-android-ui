package uk.gov.android.ui.testwrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.testwrapper.componentsv2.status.StatusOverlayDemo
import uk.gov.android.ui.testwrapper.componentsv2.topappbar.GdsTopAppBarDemo
import uk.gov.android.ui.theme.m3.GdsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val displayTopAppBarDemo: MutableState<Boolean> = remember {
                mutableStateOf(false)
            }
            GdsTheme {
                StatusOverlayDemo {
                    GdsButton(
                        text = stringResource(R.string.display_top_app_bar_demo),
                        buttonType = ButtonTypeV2.Primary(),
                        onClick = {
                            displayTopAppBarDemo.value = !displayTopAppBarDemo.value
                        }
                    )
                }
                if (displayTopAppBarDemo.value) {
                    GdsTopAppBarDemo { displayTopAppBarDemo.value = false }
                }
            }
        }
    }
}
