@file:OptIn(ExperimentalMaterial3Api::class)

package uk.gov.android.ui.patterns.dialog

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uk.gov.android.ui.componentsv2.button.GdsIconButton
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme

/**
 * A pre-configured [TopAppBar] for use with [FullScreenDialogue].
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
@Deprecated(
    message = "Deprecated and replaces by GdsTopAppBar - will be removed on 15/11/25",
    replaceWith = ReplaceWith("uk/gov/android/ui/componentsv2/topappbar/GdsTopAppBar.kt"),
    level = DeprecationLevel.WARNING,
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenDialogueTopAppBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    colors: TopAppBarColors = FullScreenDialogueTopAppBarDefaults.topAppBarColors(),
    // It's ok for this parameter to be called 'title'
    @SuppressLint("ComposableLambdaParameterNaming")
    title: @Composable (() -> Unit) = {},
) = TopAppBar(
    title = title,
    modifier = modifier,
    navigationIcon = {
        GdsIconButton(onClick = onCloseClick)
    },
    colors = colors,
    scrollBehavior = scrollBehavior,
)

/**
 * Default parameters for use with [FullScreenDialogTopAppBar].
 */
@Deprecated(
    message = "Deprecated and replaces by GdsTopAppBarDefaults.colors() - will be removed on 15/11/25",
    replaceWith = ReplaceWith("uk/gov/android/ui/componentsv2/topappbar/GdsTopAppBar.kt"),
    level = DeprecationLevel.WARNING,
)
object FullScreenDialogueTopAppBarDefaults {
    @Composable
    fun topAppBarColors() = TopAppBarDefaults.topAppBarColors(
        containerColor = colorScheme.background,
        titleContentColor = colorScheme.contentColorFor(colorScheme.background),
        // Used like this because if using the existing surfaceContainerLow it adds a hue to the color - that is managed/ added by Compose
        scrolledContainerColor = GdsLocalColorScheme.current.topBarScrolledBackground,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
internal fun FullScreenDialogueTopAppBarPreview() = GdsTheme {
    FullScreenDialogueTopAppBar(
        title = { Text("Title") },
        onCloseClick = {},
    )
}
