package uk.gov.android.ui.components.buttons

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.theme.disabled_button

sealed class ButtonType(
    val buttonColour: @Composable () -> ButtonColors,
    val fontWeight: FontWeight = FontWeight.Bold
) {
    open class PRIMARY : ButtonType(
        buttonColour = {
            primaryButtonColors()
        }
    )

    open class SECONDARY : ButtonType(
        buttonColour = {
            secondaryButtonColors()
        },
        fontWeight = FontWeight.Light
    )

    open class TERTIARY : ButtonType(
        buttonColour = {
            tertiaryButtonColors()
        },
        fontWeight = FontWeight.Light
    )

    data class ICON(
        val buttonType: ButtonType,
        val iconParameters: IconParameters
    ) : ButtonType(
        buttonColour = buttonType.buttonColour,
        fontWeight = buttonType.fontWeight
    )
}

@Composable
internal fun primaryButtonColors() = ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.primary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary),
    disabledBackgroundColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
)

@Composable
internal fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.secondary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colors.secondary),
    disabledBackgroundColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colors.primary)
)

@Composable
internal fun tertiaryButtonColors() = ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.secondary,
    contentColor = MaterialTheme.colors.primary,
    disabledBackgroundColor = disabled_button,
    disabledContentColor = androidx.compose.material3.contentColorFor(
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary
    )
)
