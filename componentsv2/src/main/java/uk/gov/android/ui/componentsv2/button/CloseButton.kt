package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.closeButtonSize
import uk.gov.android.ui.theme.m3.GdsTheme

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
) {
    IconButton(
        modifier = modifier.size(closeButtonSize),
        onClick = onClose,
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.close_button),
            tint = colorScheme.primary,
        )
    }
}

@PreviewLightDark
@Composable
internal fun CloseButtonPreview() {
    GdsTheme {
        CloseButton {}
    }
}
