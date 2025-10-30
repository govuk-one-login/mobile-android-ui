package uk.gov.android.ui.testwrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.rememberNavController
import uk.gov.android.ui.theme.m3.GdsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val startDestination = TabDestination.Components
            var selectedDestination by rememberSaveable { mutableIntStateOf(0) }

            GdsTheme {
                Scaffold { contentPadding ->
                    PrimaryTabRow(
                        selectedTabIndex = selectedDestination,
                        modifier = Modifier.padding(contentPadding)
                    ) {
                        TabDestination.entries().forEachIndexed { index, destination ->
                            Tab(
                                selected = selectedDestination == index,
                                onClick = {
                                    navController.navigate(route = destination)
                                    selectedDestination = index
                                },
                                text = {
                                    Text(
                                        text = destination.label,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            )
                        }
                    }
                    AppNavHost(navController, startDestination)
                }
            }
        }
    }
}

