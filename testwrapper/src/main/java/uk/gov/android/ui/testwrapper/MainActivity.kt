package uk.gov.android.ui.testwrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uk.gov.android.ui.testwrapper.componentsv2.status.StatusOverlayDemo
import uk.gov.android.ui.theme.m3.GdsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GdsTheme {
                StatusOverlayDemo()
            }
        }
    }
}
