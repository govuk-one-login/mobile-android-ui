package uk.gov.android.ui.components.m3.buttons

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.theme.adminButton
import uk.gov.android.ui.theme.m3_disabled
import uk.gov.android.ui.theme.m3_onDisabled

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
        }
    )

    open class QUATERNARY : ButtonType(
        buttonColour = {
            quaternaryButtonColors()
        },
        fontWeight = FontWeight.Light
    )

    open class ADMIN : ButtonType(
        buttonColour = {
            adminButtonColors()
        }
    )

    open class ERROR : ButtonType(
        buttonColour = {
            errorButtonColors()
        }
    )

    data class ICON(
        val parentButtonType: ButtonType,
        val iconParameters: IconParameters
    ) : ButtonType(
        buttonColour = parentButtonType.buttonColour,
        fontWeight = parentButtonType.fontWeight
    )
}

@Composable
internal fun primaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.primary,
    contentColor = colorScheme.onPrimary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled
)

@Composable
internal fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.secondary,
    contentColor = colorScheme.onSecondary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled
)

@Composable
internal fun tertiaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.tertiary,
    contentColor = colorScheme.onTertiary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled
)

@Composable
internal fun quaternaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.secondary,
    contentColor = colorScheme.primary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled
)

@Composable
internal fun adminButtonColors() = ButtonDefaults.buttonColors(
    containerColor = adminButton,
    contentColor = Color.White,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled
)

@Composable
internal fun errorButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.error,
    contentColor = colorScheme.onError,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled
)
