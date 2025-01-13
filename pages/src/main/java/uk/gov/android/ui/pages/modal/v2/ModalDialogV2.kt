package uk.gov.android.ui.pages.modal.v2

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
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
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.pages.LandingPage
import uk.gov.android.ui.pages.LandingPageParameters
import uk.gov.android.ui.pages.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDialogV2(
    parameters: ModalDialogParametersV2,
    content: @Composable () -> Unit,
) {
    with(parameters) {
        GdsTheme {
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
                            IconButton(onClick = onClose) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = "Localized description",
                                    tint = colorScheme.primary,
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior,
                    )
                },
            ) { innerPadding ->
                Column(
                    modifier = Modifier
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

data class ModalPreviewParameters(
    val modalDialogParameters: ModalDialogParametersV2,
    val content: @Composable () -> Unit,
)

class ModalDialogPreviewProvider : PreviewParameterProvider<ModalPreviewParameters> {
    override val values: Sequence<ModalPreviewParameters> = sequenceOf(
        ModalPreviewParameters(ModalDialogParametersV2(title = "Title")) { },

        ModalPreviewParameters(ModalDialogParametersV2()) {
            LandingPage(
                landingPageParameters = LandingPageParameters(
                    title = R.string.preview__modal_v2_example3_title,
                    content = listOf(
                        GdsContentText.GdsContentTextString(
                            intArrayOf(R.string.preview__modal_v2_example3_text),
                        ),
                    ),
                    primaryButtonText = R.string.preview__modal_v2_example3_primary_button,
                ),
            )
        },
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun ModalDialogPreview(
    @PreviewParameter(ModalDialogPreviewProvider::class)
    parameters: ModalPreviewParameters,
) {
    ModalDialogV2(
        parameters = parameters.modalDialogParameters,
        content = parameters.content,
    )
}
