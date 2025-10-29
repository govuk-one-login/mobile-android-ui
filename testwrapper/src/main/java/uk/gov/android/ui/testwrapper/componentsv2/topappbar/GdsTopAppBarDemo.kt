package uk.gov.android.ui.testwrapper.componentsv2.topappbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.menu.GdsMenu
import uk.gov.android.ui.componentsv2.menu.GdsMenuContent
import uk.gov.android.ui.componentsv2.status.GdsStatusOverlay
import uk.gov.android.ui.componentsv2.topappbar.TopAppBarAlignment
import uk.gov.android.ui.componentsv2.topappbar.TopBarActionButton
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.testwrapper.R as TestWrapperR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsTopAppBarDemo() {
    val statusOverlayState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val alignment = remember { mutableStateOf(TopAppBarAlignment.Start) }
    val showNavIconButton = remember { mutableStateOf(true) }
    val showActionIconButton = remember { mutableStateOf(true) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val itemTitle1 = "Item does something"
    val itemTitle2 = "Item does nothing"
    Scaffold(
        snackbarHost = {
            GdsStatusOverlay(
                hostState = statusOverlayState,
                modifier = Modifier.padding(horizontal = spacingDouble),
            )
        },
        topBar = {
            GdsTopAppBar(
                title = stringResource(R.string.title),
                navigationButton = if (showNavIconButton.value) {
                    GdsIconButtonDefaults.defaultCloseContent()
                } else null,
                onClick = {
                    scope.launch {
                        statusOverlayState.showSnackbar("Dismiss button pressed")
                    }
                },
                actions = if (showActionIconButton.value) {
                    persistentListOf(
                        TopBarActionButton(
                            content = GdsIconButtonDefaults.defaultInfoContent(),
                            onClick = {
                                isMenuExpanded = !isMenuExpanded
                                println("Click action button")
                            }
                        ),
                    )
                } else null,
                scrollBehaviour = scrollBehavior,
                alignment = alignment.value,
                menu = {
                    GdsMenu(
                        expanded = isMenuExpanded,
                        content = persistentListOf(
                            GdsMenuContent(itemTitle1) {
                                scope.launch {
                                    statusOverlayState.showSnackbar("Selected: $itemTitle1")
                                }
                            },
                            GdsMenuContent(itemTitle2) {}
                        ),
                        onDismissRequest = {
                            isMenuExpanded = !isMenuExpanded
                        }
                    )
                }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            FormattedButton(titleRes = TestWrapperR.string.toggle_title) {
                if (alignment.value == TopAppBarAlignment.Start) {
                    alignment.value = TopAppBarAlignment.Centre
                } else {
                    alignment.value = TopAppBarAlignment.Start
                }
            }
            FormattedButton(titleRes = TestWrapperR.string.show_nav_icon_button) {
                showNavIconButton.value = !showNavIconButton.value
            }
            FormattedButton(titleRes = TestWrapperR.string.show_action_icon_button) {
                showActionIconButton.value = !showActionIconButton.value
            }
            repeat(100) {
                Text(
                    text = "Test",
                    modifier = Modifier.padding(horizontal = smallPadding)
                )
            }
        }
    }
}

@Composable
fun FormattedButton(
    titleRes: Int,
    onClick: () -> Unit
) {
    GdsButton(
        text = stringResource(titleRes),
        buttonType = ButtonTypeV2.Secondary(),
        onClick = onClick
    )
}
