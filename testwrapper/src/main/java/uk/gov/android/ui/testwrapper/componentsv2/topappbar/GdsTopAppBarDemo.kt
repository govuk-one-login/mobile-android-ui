package uk.gov.android.ui.testwrapper.componentsv2.topappbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.menu.GdsMenu
import uk.gov.android.ui.componentsv2.menu.GdsMenuContent
import uk.gov.android.ui.componentsv2.topappbar.TopAppBarAlignment
import uk.gov.android.ui.componentsv2.topappbar.TopBarActionButton
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.testwrapper.R as TestWrapperR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsTopAppBarDemo(
    dismiss: () -> Unit,
    onMenuSelect: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val alignment = remember { mutableStateOf(TopAppBarAlignment.Start) }
    val showNavIconButton = remember { mutableStateOf(true) }
    val showActionIconButton = remember { mutableStateOf(true) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val itemTitle1 = "Item does something"
    val itemTitle2 = "Item does nothing"
    Scaffold(
        topBar = {
            GdsTopAppBar(
                title = stringResource(R.string.title),
                navigationButton = if (showNavIconButton.value) {
                    GdsIconButtonDefaults.defaultCloseContent()
                } else null,
                onClick = dismiss,
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
                                onMenuSelect(itemTitle1)
                                dismiss()
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
            FormattedButton {
                if (alignment.value == TopAppBarAlignment.Start) {
                    alignment.value = TopAppBarAlignment.Centre
                } else {
                    alignment.value = TopAppBarAlignment.Start
                }
            }
            FormattedButton { showNavIconButton.value = !showNavIconButton.value }
            FormattedButton { showActionIconButton.value = !showActionIconButton.value }
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
    onClick: () -> Unit
) {
    GdsButton(
        text = stringResource(TestWrapperR.string.show_nav_icon_button),
        buttonType = ButtonTypeV2.Secondary(),
        onClick = onClick
    )
}
