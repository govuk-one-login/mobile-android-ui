@file:OptIn(ExperimentalMaterial3Api::class)

package uk.gov.android.ui.patterns.dialog

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.button.CloseButton
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * A pre-configured [TopAppBar] for use with [FullScreenDialog].
 *
 * This composable provides a standard layout for a top app bar in a full-screen dialog,
 * including a close button in the navigation icon slot.
 *
 * @param onCloseClick The callback to be invoked when the close button is clicked.
 * @param modifier The [Modifier] to be applied to the top app bar.
 * @param colors The [TopAppBarColors] to be used for the top app bar.
 *   See [FullScreenDialogTopAppBarDefaults.topAppBarColors].
 * @param title The title of the top app bar. Use [Text] for this.
 */
@Composable
fun FullScreenDialogTopAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = FullScreenDialogTopAppBarDefaults.topAppBarColors(),
    // It's ok for this parameter to be called 'title'
    @SuppressLint("ComposableLambdaParameterNaming")
    title: @Composable (() -> Unit) = {},
) = TopAppBar(
    title = title,
    modifier = modifier,
    navigationIcon = {
        CloseButton(onClose = onCloseClick)
    },
    colors = colors,
)

/**
 * Default parameters for use with [FullScreenDialogTopAppBar].
 */
object FullScreenDialogTopAppBarDefaults {
    @Composable
    fun topAppBarColors() = TopAppBarDefaults.topAppBarColors(
        containerColor = colorScheme.background,
        titleContentColor = colorScheme.contentColorFor(colorScheme.background),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
internal fun FullScreenDialogTopAppBarPreview() = GdsTheme {
    FullScreenDialogTopAppBar(
        title = { Text("Title") },
        onCloseClick = {},
    )
}
