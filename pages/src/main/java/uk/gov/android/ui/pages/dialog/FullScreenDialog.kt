package uk.gov.android.ui.pages.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import uk.gov.android.ui.components.m3.buttons.CloseButton
import uk.gov.android.ui.theme.smallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialog(
    onDismissRequest: () -> Unit = { },
    modifier: Modifier = Modifier,
    title: String? = null,
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
                topBar = {
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
            ) { innerPadding ->
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(smallPadding)
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    content()
                }
            }
        }
    }
}

data class FullScreenDialogPreviewParameters(
    val onDismissRequest: () -> Unit = { },
    val title: String? = "Title",
    val content: @Composable () -> Unit = { },
)

class FullScreenDialogPreviewProvider : PreviewParameterProvider<FullScreenDialogPreviewParameters> {
    override val values: Sequence<FullScreenDialogPreviewParameters> = sequenceOf(
        FullScreenDialogPreviewParameters(),

        FullScreenDialogPreviewParameters(title = null) {
            Text("Content")
        },
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun ModalDialogPreview(
    @PreviewParameter(FullScreenDialogPreviewProvider::class)
    parameters: FullScreenDialogPreviewParameters,
) {
    FullScreenDialog(
        title = parameters.title,
        content = parameters.content,
    )
}
