package uk.gov.android.ui.testwrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.android.ui.testwrapper.componentsv2.status.StatusOverlayDemo
import uk.gov.android.ui.testwrapper.ui.theme.MobileandroiduiTheme
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
