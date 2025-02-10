package uk.gov.android.ui.patterns.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import uk.gov.android.ui.componentsv2.button.CloseButton

/**
 * Full screen dialog
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
fun FullScreenDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    FullScreenDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        topAppBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.background,
                    titleContentColor = colorScheme.contentColorFor(colorScheme.background),
                ),
                title = {
                    title?.let {
                        Text(title)
                    }
                },
                navigationIcon = {
                    CloseButton(onClose = onDismissRequest)
                },
                scrollBehavior = scrollBehavior,
            )
        },
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
 * @param onDismissRequest Executes when the user tries to dismiss the dialog.
 * @param modifier Modifier to be applied to the layout corresponding to the dialog content
 * @param topAppBar Requires any type of [TopAppBar] to allow customisation
 * @param content The content to be displayed inside the dialog.
 *
 * **Used in [FullScreenDialog] composition.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialog(
    onDismissRequest: () -> Unit,
    topAppBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismissRequest,
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = { topAppBar() },
            ) { innerPadding ->
                Column(
                    modifier = modifier.height(IntrinsicSize.Max)
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    content()
                }
            }
        }
    }
}

internal data class FullScreenDialogPreviewParameters(
    val onDismissRequest: () -> Unit = { },
    val title: String? = "Title",
    val content: @Composable () -> Unit = { },
)

internal class FullScreenDialogPreviewProvider : PreviewParameterProvider<FullScreenDialogPreviewParameters> {
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
    @PreviewParameter(FullScreenDialogPreviewProvider::class)
    parameters: FullScreenDialogPreviewParameters,
) {
    FullScreenDialog(
        onDismissRequest = { },
        title = parameters.title,
        content = parameters.content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
internal fun ModalDialogWithCustomisedTopAppBarPreview(
    @PreviewParameter(FullScreenDialogPreviewProvider::class)
    parameters: FullScreenDialogPreviewParameters,
) {
    FullScreenDialog(
        onDismissRequest = { },
        topAppBar = {
            TopAppBar(
                title = { Text("") },
            )
        },
        content = parameters.content,
    )
}
