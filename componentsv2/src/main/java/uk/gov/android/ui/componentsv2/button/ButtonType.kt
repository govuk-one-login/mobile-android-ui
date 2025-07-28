@file:Suppress("ForbiddenComment")

package uk.gov.android.ui.componentsv2.button

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.adminButton

@Immutable
sealed class ButtonType {
    data object Primary : ButtonType()

    data object Secondary : ButtonType()

    data object Tertiary : ButtonType()

    data object Quaternary : ButtonType()

    data object Admin : ButtonType()

    data object Error : ButtonType()

    data class Custom(
        val contentColor: Color,
        val containerColor: Color,
        val fontWeight: FontWeight = FontWeight.Light,
    ) : ButtonType()

    data class Icon(
        val buttonColors: ButtonColors,
        val iconImage: ImageVector,
        val contentDescription: String,
        val fontWeight: FontWeight = FontWeight.Light,
        val isIconTrailing: Boolean = true,
        val shadowColor: Color = Color.Transparent,
    ) : ButtonType()
}

internal fun ButtonType.fontWeight() = when (this) {
    ButtonType.Admin,
    ButtonType.Error,
    ButtonType.Primary,
    ButtonType.Tertiary,
    -> FontWeight.Bold

    ButtonType.Quaternary,
    ButtonType.Secondary,
    -> FontWeight.Light

    is ButtonType.Custom -> fontWeight
    is ButtonType.Icon -> fontWeight
}

@Composable
fun ButtonType.buttonColors() = when (this) {
    is ButtonType.Admin -> adminButtonColors()
    is ButtonType.Custom -> customButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    is ButtonType.Error -> errorButtonColors()
    is ButtonType.Icon -> buttonColors
    is ButtonType.Primary -> primaryButtonColors()
    is ButtonType.Quaternary -> quaternaryButtonColors()
    is ButtonType.Secondary -> secondaryButtonColors()
    is ButtonType.Tertiary -> tertiaryButtonColors()
}

@Composable
internal fun primaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.primary,
    contentColor = colorScheme.onPrimary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Composable
internal fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = colorScheme.secondary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Composable
internal fun tertiaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = colorScheme.secondary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Composable
internal fun quaternaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = colorScheme.secondary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Composable
internal fun adminButtonColors() = ButtonDefaults.buttonColors(
    containerColor = adminButton,
    contentColor = Color.White,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Composable
internal fun errorButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.error,
    contentColor = colorScheme.onError,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Composable
fun customButtonColors(containerColor: Color, contentColor: Color) = ButtonDefaults.buttonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Composable
fun focusStateButtonColors() = ButtonDefaults.buttonColors(
    containerColor = GdsLocalColorScheme.current.focusState,
    contentColor = GdsLocalColorScheme.current.focusStateContent,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)
