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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.persistentListOf
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.topappbar.TopAppBarAlignment
import uk.gov.android.ui.componentsv2.topappbar.TopBarActionButton
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.testwrapper.R as TestWrapperR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsTopAppBarDemo(
    dismiss: () -> Unit
) {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val alignment = remember { mutableStateOf(TopAppBarAlignment.Start) }
    val showNavIconButton = remember { mutableStateOf(true) }
    val showActionIconButton = remember { mutableStateOf(true) }
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
                            onClick = { println("Click action button") }
                        )
                    )
                } else null,
                scrollBehaviour = scrollBehavior,
                alignment = alignment.value
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        Column(Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .verticalScroll(scrollState)
        ) {
            GdsButton(
                text = stringResource(TestWrapperR.string.change_bar_alignment),
                buttonType = ButtonTypeV2.Secondary(),
                onClick = {
                    if (alignment.value == TopAppBarAlignment.Start) {
                        alignment.value = TopAppBarAlignment.Centre
                    } else {
                        alignment.value = TopAppBarAlignment.Start
                    }
                }
            )
            GdsButton(
                text = stringResource(TestWrapperR.string.show_nav_icon_button),
                buttonType = ButtonTypeV2.Secondary(),
                onClick = { showNavIconButton.value = !showNavIconButton.value }
            )
            GdsButton(
                text = stringResource(TestWrapperR.string.show_action_icon_button),
                buttonType = ButtonTypeV2.Secondary(),
                onClick = { showActionIconButton.value = !showActionIconButton.value }
            )
            repeat(100) {
                Text(
                    text = "Test",
                    modifier = Modifier.padding(horizontal = smallPadding)
                )
            }
        }
    }
}