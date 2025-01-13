package uk.gov.android.ui.pages.modal

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonType
import uk.gov.android.ui.components.m3.buttons.GdsButton
import uk.gov.android.ui.pages.modal.v2.ModalDialogParametersV2
import uk.gov.android.ui.pages.modal.v2.ModalDialogV2
import uk.gov.android.ui.theme.smallPadding

@Composable
@Deprecated(message = "Please upgrade to ModalDialogV2")
fun ModalDialog(parameters: ModalDialogParameters) {
    with(parameters) {
        ModalDialogV2(
            parameters = ModalDialogParametersV2(onClose = parameters.onClose),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(
                        color = colorScheme.contentColorFor(colorScheme.background),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = smallPadding)
                            .semantics { heading() },
                        style = MaterialTheme.typography.displaySmall,
                        text = title,
                        textAlign = TextAlign.Start,
                    )
                    BulletedText(
                        parameters = BulletedTextParameters(
                            modifier = Modifier.fillMaxWidth(),
                            header = header,
                            bullets = bullets,
                            footer = footer,
                        ),
                    )
                }
                GdsButton(
                    buttonParameters = ButtonParameters(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(smallPadding),
                        buttonType = buttonParams.buttonType,
                        text = buttonParams.text,
                        isEnabled = buttonParams.isEnabled,
                        onClick = buttonParams.onClick,
                    ),
                )
            }
        }
    }
}

internal val modalDialogPreviewParams = ModalDialogParameters(
    title = "Are you sure you want to sign out?",
    header = buildAnnotatedString {
        append("Signing out will switch off your preferences for:")
    },
    bullets = listOf(
        "using biometrics or your phone's pin or pattern to unlock the app",
        "sharing analytics about how you use the app",
        "Third extremely long bullet line that is going to show how this wraps",
    ),
    footer = buildAnnotatedString {
        append("You'll be asked to set these preferences again next time you sign in.")
    },
    buttonParams = ModalDialogParameters.ButtonParameters(
        text = "Sign out and delete preferences",
        buttonType = ButtonType.PRIMARY(),
        isEnabled = true,
        onClick = {},
    ),
    onClose = {},
)

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun ModalDialogPreview() {
    ModalDialog(
        parameters = modalDialogPreviewParams,
    )
}
