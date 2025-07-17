package uk.gov.android.ui.patterns.dialog

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

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
            FullScreenDialogueTopAppBar(
                title = {
                    title?.let {
                        Text(title)
                    }
                },
                onCloseClick = onDismissRequest,
                scrollBehavior = scrollBehaviour,
            )
        },
        onBack = onBack,
        content = content,
    )
}

/**
 * Full screen dialog
 *
 * Use this pattern for a task made up of a series of steps/ information
 *
 * **This pattern allows for implementing custom [TopAppBar] provided as a parameter.**
 *
 * ‘Close’ icon in Android full screen dialog allow users to exit the journey easily
 * whenever they need. Users can use the device back button or gestures to return to the
 * previous screen.
 *
 * @param modifier Modifier to be applied to the layout corresponding to the dialog content
 * @param topAppBar Requires any type of [TopAppBar] to allow customisation.
 *   See [FullScreenDialogTopAppBar] for a pre-configured implementation.
 * @param onBack Overrides the device back button - if **null**, it will keep the default back button behaviour
 * @param content The content to be displayed inside the dialog.
 *
 * **Used in [FullScreenDialog] composition.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialogue(
    topAppBar: @Composable (TopAppBarScrollBehavior?) -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    content: @Composable (ScrollState) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
    ) {
        BoxWithConstraints {
            val scrollState = rememberScrollState()
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
            Scaffold(
                topBar = { topAppBar(scrollBehavior) },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            ) { innerPadding ->
                Column(
                    modifier = modifier
                        .height(this.maxHeight)
                        .padding(innerPadding)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    content(scrollState)
                }
            }
        }
        if (onBack != null) {
            BackHandler { onBack() }
        }
    }
}

internal data class FullScreenDialoguePreviewParameters(
    val onDismissRequest: () -> Unit = { },
    val title: String? = "Title",
    val content: @Composable () -> Unit = { },
)

internal class FullScreenDialoguePreviewProvider :
    PreviewParameterProvider<FullScreenDialogPreviewParameters> {
    override val values: Sequence<FullScreenDialogPreviewParameters> =
        sequenceOf(
            FullScreenDialogPreviewParameters(),
            FullScreenDialogPreviewParameters(title = null) {
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
    FullScreenDialogue(
        onDismissRequest = { },
        title = parameters.title,
        content = { parameters.content },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
internal fun ModalDialogWithCustomisedTopAppBarPreview(
    @PreviewParameter(FullScreenDialoguePreviewProvider::class)
    parameters: FullScreenDialoguePreviewParameters,
) {
    FullScreenDialogue(
        topAppBar = {
            FullScreenDialogTopAppBar(
                title = { Text("") },
                onCloseClick = {},
            )
        },
        content = { parameters.content },
    )
}
