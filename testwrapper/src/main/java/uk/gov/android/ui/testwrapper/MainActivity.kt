package uk.gov.android.ui.testwrapper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uk.gov.android.ui.testwrapper.componentsv2.ComponentDetail
import uk.gov.android.ui.testwrapper.componentsv2.Components
import uk.gov.android.ui.testwrapper.componentsv2.ComponentsDestination
import uk.gov.android.ui.testwrapper.componentsv2.dialogueItems
import uk.gov.android.ui.testwrapper.componentsv2.listItems
import uk.gov.android.ui.testwrapper.componentsv2.qrScanningItems
import uk.gov.android.ui.testwrapper.componentsv2.radioItems
import uk.gov.android.ui.testwrapper.componentsv2.statusItems
import uk.gov.android.ui.testwrapper.componentsv2.topAppBarItems
import uk.gov.android.ui.testwrapper.patterns.Patterns
import uk.gov.android.ui.testwrapper.patterns.PatternsDestination
import uk.gov.android.ui.testwrapper.theme.Theme
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val startDestination = TabDestination.COMPONENTS
            var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

            GdsTheme {
                Scaffold { contentPadding ->
                    PrimaryTabRow(
                        selectedTabIndex = selectedDestination,
                        modifier = Modifier.padding(contentPadding)
                    ) {
                        TabDestination.entries.forEachIndexed { index, destination ->
                            Tab(
                                selected = selectedDestination == index,
                                onClick = {
                                    navController.navigate(route = destination.route)
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

enum class TabDestination(
    val route: String,
    val label: String
) {
    COMPONENTS("components", "Components (v2)"),
    PATTERNS("patterns", "Patterns"),
    THEME("theme", "Theme")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: TabDestination,
    modifier: Modifier = Modifier
) {
    val tabPagesOffsetPadding = 50.dp
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        val mod = modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(top = tabPagesOffsetPadding)
        TabDestination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    TabDestination.COMPONENTS -> Components(mod, navController)
                    TabDestination.PATTERNS -> Patterns(mod, navController)
                    TabDestination.THEME -> Theme(mod)
                }
            }
        }
        ComponentsDestination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    ComponentsDestination.LIST -> {
                        ComponentListDetail(listItems, mod)
                    }

                    ComponentsDestination.INPUTS -> {
                        ComponentListDetail(radioItems, mod)
                    }

                    ComponentsDestination.TOPAPPBAR -> {
                        ComponentListDetail(topAppBarItems, mod)
                    }

                    ComponentsDestination.DIALOGUE -> {
                        ComponentListDetail(dialogueItems, mod)
                    }

                    ComponentsDestination.STATUS -> {
                        ComponentListDetail(statusItems, mod)
                    }

                    ComponentsDestination.QR_SCANNING -> {
                        ComponentListDetail(qrScanningItems, mod)
                    }

                    else -> {
                        Placeholder(
                            label = destination.label,
                            modifier = mod.padding(smallPadding)
                        )
                    }
                }
            }
        }
        PatternsDestination.entries.forEach { destination ->
            composable(destination.route) {
                Placeholder(
                    label = destination.label,
                    modifier = mod.padding(smallPadding)
                )
            }
        }
    }
}

@Composable
private fun ComponentListDetail(
    items: List<DetailItem>,
    modifier: Modifier
) {
    ListDetail(
        items = items,
        detail = { detailItem ->
            ComponentDetail(detailItem)
        },
        modifier = modifier
    )
}

@Composable
fun Placeholder(label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text("Add list / detail for $label")
    }
}
