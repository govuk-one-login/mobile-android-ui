package uk.gov.android.ui.components.m3.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
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
    containerColor = MaterialTheme.colorScheme.primary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary),
    disabledContainerColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
)

@Composable
internal fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.secondary),
    disabledContainerColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
)

@Composable
internal fun tertiaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.secondary,
    contentColor = MaterialTheme.colorScheme.primary,
    disabledContainerColor = disabled_button,
    disabledContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
)
