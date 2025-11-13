package uk.gov.android.ui.testwrapper.componentsv2.topappbar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.ButtonTypeV2
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.menu.GdsMenu
import uk.gov.android.ui.componentsv2.menu.GdsMenuContent
import uk.gov.android.ui.componentsv2.status.GdsStatusOverlay
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.topappbar.TopAppBarAlignment
import uk.gov.android.ui.componentsv2.topappbar.TopBarActionButton
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.spacingDouble
import uk.gov.android.ui.testwrapper.R as TestWrapperR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GdsTopAppBarDemo(
    modifier: Modifier = Modifier,
) {
    val statusOverlayState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val (alignment, updateAlignment) = remember { mutableStateOf(TopAppBarAlignment.Start) }
    val (showNavIconButton, updateNavIconButton) = remember { mutableStateOf(true) }
    val (showActionIconButton, updateActionIconButton) = remember { mutableStateOf(true) }
    val (isMenuExpanded, updateMenuExpanded) = remember { mutableStateOf(false) }
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
            GdsTopAppBarConfiguration(
                showNavIconButton = showNavIconButton,
                scope = scope,
                statusOverlayState = statusOverlayState,
                showActionIconButton = showActionIconButton,
                updateMenuExpanded = updateMenuExpanded,
                isMenuExpanded = isMenuExpanded,
                scrollBehavior = scrollBehavior,
                alignment = alignment,
                itemTitle1 = itemTitle1,
                itemTitle2 = itemTitle2,
            )
        },
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { paddingValues ->
        GdsTopAppBarDemoContent(
            paddingValues = paddingValues,
            scrollState = scrollState,
            alignment = alignment,
            updateAlignment = updateAlignment,
            updateNavIconButton = updateNavIconButton,
            showNavIconButton = showNavIconButton,
            updateActionIconButton = updateActionIconButton,
            showActionIconButton = showActionIconButton,
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun GdsTopAppBarConfiguration(
    showNavIconButton: Boolean,
    scope: CoroutineScope,
    statusOverlayState: SnackbarHostState,
    showActionIconButton: Boolean,
    updateMenuExpanded: (Boolean) -> Unit,
    isMenuExpanded: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    alignment: TopAppBarAlignment,
    itemTitle1: String,
    itemTitle2: String,
    modifier: Modifier = Modifier,
) {
    GdsTopAppBar(
        modifier = modifier,
        title = stringResource(R.string.title),
        navigationButton =
        if (showNavIconButton) {
            GdsIconButtonDefaults.defaultCloseContent()
        } else {
            null
        },
        onClick = {
            scope.launch {
                statusOverlayState.showSnackbar("Dismiss button pressed")
            }
        },
        actions =
        generateActions(showActionIconButton, updateMenuExpanded, isMenuExpanded),
        scrollBehaviour = scrollBehavior,
        alignment = alignment,
        menu = {
            GdsTopAppBarDemoMenu(
                isMenuExpanded = isMenuExpanded,
                itemTitle1 = itemTitle1,
                scope = scope,
                statusOverlayState = statusOverlayState,
                itemTitle2 = itemTitle2,
                updateMenuExpanded = updateMenuExpanded,
            )
        },
    )
}

@Composable
private fun generateActions(
    showActionIconButton: Boolean,
    updateMenuExpanded: (Boolean) -> Unit,
    isMenuExpanded: Boolean,
): PersistentList<TopBarActionButton>? = if (showActionIconButton) {
    persistentListOf(
        TopBarActionButton(
            content = GdsIconButtonDefaults.defaultInfoContent(),
            onClick = {
                updateMenuExpanded(!isMenuExpanded)
                println("Click action button")
            },
        ),
    )
} else {
    null
}

@Composable
private fun GdsTopAppBarDemoMenu(
    isMenuExpanded: Boolean,
    itemTitle1: String,
    scope: CoroutineScope,
    statusOverlayState: SnackbarHostState,
    itemTitle2: String,
    updateMenuExpanded: (Boolean) -> Unit,
) {
    GdsMenu(
        expanded = isMenuExpanded,
        content =
        persistentListOf(
            GdsMenuContent(itemTitle1) {
                scope.launch {
                    statusOverlayState.showSnackbar("Selected: $itemTitle1")
                }
            },
            GdsMenuContent(itemTitle2) {},
        ),
        onDismissRequest = {
            updateMenuExpanded(!isMenuExpanded)
        },
    )
}

private const val APP_BAR_DEMO_TEXT_COUNT = 100

@Composable
private fun GdsTopAppBarDemoContent(
    paddingValues: PaddingValues,
    scrollState: ScrollState,
    alignment: TopAppBarAlignment,
    updateAlignment: (TopAppBarAlignment) -> Unit,
    updateNavIconButton: (Boolean) -> Unit,
    showNavIconButton: Boolean,
    updateActionIconButton: (Boolean) -> Unit,
    showActionIconButton: Boolean,
    modifier: Modifier = Modifier,
    textIterationCount: Int = APP_BAR_DEMO_TEXT_COUNT,
) {
    Column(
        modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        FormattedButton(titleRes = TestWrapperR.string.toggle_title) {
            if (alignment == TopAppBarAlignment.Start) {
                updateAlignment(TopAppBarAlignment.Centre)
            } else {
                updateAlignment(TopAppBarAlignment.Start)
            }
        }
        FormattedButton(titleRes = TestWrapperR.string.show_nav_icon_button) {
            updateNavIconButton(!showNavIconButton)
        }
        FormattedButton(titleRes = TestWrapperR.string.show_action_icon_button) {
            updateActionIconButton(!showActionIconButton)
        }
        repeat(textIterationCount) {
            Text(
                text = "Test",
                modifier = Modifier.padding(horizontal = smallPadding),
            )
        }
    }
}

@Composable
fun FormattedButton(
    titleRes: Int,
    onClick: () -> Unit,
) {
    GdsButton(
        text = stringResource(titleRes),
        buttonType = ButtonTypeV2.Secondary(),
        onClick = onClick,
    )
}
