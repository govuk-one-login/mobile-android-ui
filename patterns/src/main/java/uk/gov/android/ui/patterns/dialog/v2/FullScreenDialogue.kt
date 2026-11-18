package uk.gov.android.ui.patterns.dialog.v2

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.button.GdsIconButtonDefaults
import uk.gov.android.ui.componentsv2.topappbar.GdsTopAppBar
import uk.gov.android.ui.componentsv2.utils.windowInsetsPadding
import uk.gov.android.ui.patterns.dialog.FullScreenDialogue
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * Full screen dialogue
 *
 * Use this pattern for a task made up of a series of steps
 *
 * Examples of tasks that are self-contained or actions that require a series of tasks
 * - ID Check
 * - Add document
 * - Sign out
 *
 * ‘Close’ icon in Android full screen dialog allow users to exit the journey easily
 * whenever they need. Users can use the device back button or gestures to return to the
 * previous screen.
 *
 * @param onDismissRequest Executes when the user tries to dismiss the dialog.
 * @param modifier Modifier to be applied to the layout corresponding to the dialog content
 * @param title The content to be displayed inside the dialog.
 * @param content The content to be displayed inside the dialog.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialogue(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    onBack: (() -> Unit)? = null,
    content: @Composable (ScrollState) -> Unit,
) {
    FullScreenDialogue(
        modifier = modifier,
        topAppBar = { scrollBehaviour ->
            GdsTopAppBar(
                title = title,
                navigationButton = GdsIconButtonDefaults.defaultCloseContent(),
                onClick = onDismissRequest,
                scrollBehaviour = scrollBehaviour,
            )
        },
        onBack = onBack,
        content = content,
    )
}

/**
 * Full screen dialog
 *
 * Use this pattern for a task made up of a series of steps or information
 *
 * This implementation does not contain the Dialog composable so any dialog behaviour will need
 * to be implemented by the calling class.
 *
 * ‘Close’ icon in Android full screen dialog allow users to exit the journey easily
 * whenever they need. Users can use the device back button or gestures to return to the
 * previous screen.
 *
 * @param modifier Modifier to be applied to the layout corresponding to the dialog content
 * @param topAppBar Requires any type of [TopAppBar] to allow customisation.
 *   See [GdsTopAppBar] for a pre-configured implementation.
 * @param onBack Overrides the device back button - if **null**, it will keep the default back button behaviour
 * @param content The content to be displayed inside the dialog.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialogue(
    topAppBar: @Composable (TopAppBarScrollBehavior?) -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    content: @Composable (ScrollState) -> Unit,
) = Surface {
    val scrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = { topAppBar(scrollBehavior) },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    PaddingValues(
                        start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                        end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                        top = innerPadding.calculateTopPadding(),
                        // No bottom padding because the scrolling content should handle that
                    ),
                ),
        ) {
            content(scrollState)
        }
    }
    if (onBack != null) {
        BackHandler { onBack() }
    }
}

internal data class FullScreenDialoguePreviewParameters(
    val onDismissRequest: () -> Unit = { },
    val title: String? = "Title",
    val content: @Composable () -> Unit = { },
)

internal class FullScreenDialoguePreviewProvider :
    PreviewParameterProvider<FullScreenDialoguePreviewParameters> {
    override val values: Sequence<FullScreenDialoguePreviewParameters> =
        sequenceOf(
            FullScreenDialoguePreviewParameters(),
            FullScreenDialoguePreviewParameters(title = null) {
                Text("Content")
            },
        )
}

@PreviewLightDark
@Composable
internal fun ModalDialogPreview(
    @PreviewParameter(FullScreenDialoguePreviewProvider::class)
    parameters: FullScreenDialoguePreviewParameters,
) {
    GdsTheme {
        FullScreenDialogue(
            onDismissRequest = { },
            title = parameters.title,
            content = { parameters.content },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
internal fun ModalDialogWithCustomisedTopAppBarPreview(
    @PreviewParameter(FullScreenDialoguePreviewProvider::class)
    parameters: FullScreenDialoguePreviewParameters,
) {
    GdsTheme {
        FullScreenDialogue(
            topAppBar = {
                GdsTopAppBar()
            },
            content = { parameters.content },
        )
    }
}
