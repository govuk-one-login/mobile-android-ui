@file:Suppress("TooManyFunctions")

package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.RippleDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.adminButton
import uk.gov.android.ui.theme.smallPadding

@Immutable
sealed class ButtonTypeV2(
    open val textStyle: TextStyle = ExtraTypography.bodyLargeBold,
) {
    data class Primary(
        override val textStyle: TextStyle = ExtraTypography.bodyLargeBold,
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Secondary(
        override val textStyle: TextStyle = Typography.bodyLarge,
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Tertiary(
        override val textStyle: TextStyle = ExtraTypography.bodyLargeBold,
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Quaternary(
        override val textStyle: TextStyle = ExtraTypography.bodyLargeBold,
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Destructive(
        override val textStyle: TextStyle = ExtraTypography.bodyLargeBold,
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Admin(
        override val textStyle: TextStyle = ExtraTypography.bodyLargeBold,
    ) : ButtonTypeV2(textStyle = textStyle)

    data class SecondaryDestructive(
        override val textStyle: TextStyle = Typography.bodyLarge,
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Custom(
        val contentColor: Color,
        val containerColor: Color,
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Light),
    ) : ButtonTypeV2(textStyle = textStyle)

    @Suppress("TooGenericExceptionThrown")
    data class Icon(
        val buttonColors: ButtonColors,
        val icon: ImageVector? = null,
        var contentDescription: String? = null,
        val isIconTrailing: Boolean = true,
        val shadowColor: Color = Color.Transparent,
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Light),
    ) : ButtonTypeV2(textStyle = textStyle) {
        init {
            if (icon != null && contentDescription == null) {
                throw Exception("You must provide a content description for the icon used")
            }
        }
    }
}

@Composable
fun ButtonTypeV2.buttonColors() = when (this) {
    is ButtonTypeV2.Admin -> GdsButtonDefaults.defaultAdminColors()
    is ButtonTypeV2.Custom -> GdsButtonDefaults.customColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    is ButtonTypeV2.Destructive -> GdsButtonDefaults.defaultErrorColors()
    is ButtonTypeV2.SecondaryDestructive -> GdsButtonDefaults.defaultSecondaryDestructiveColors()
    is ButtonTypeV2.Icon -> buttonColors
    is ButtonTypeV2.Primary -> GdsButtonDefaults.defaultPrimaryColors()
    is ButtonTypeV2.Quaternary -> GdsButtonDefaults.defaultQuaternaryColors()
    is ButtonTypeV2.Secondary -> GdsButtonDefaults.defaultSecondaryColors()
    is ButtonTypeV2.Tertiary -> GdsButtonDefaults.defaultTertiaryColors()
}

object GdsButtonDefaults {
    val spinnerDefaultSize: Dp = 22.dp
    val defaultShape: Shape = RectangleShape
    fun customRoundedShape(radius: Dp): Shape = RoundedCornerShape(radius)

    val defaultModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = smallPadding)
        .padding(top = smallPadding)

    private const val RIPPLE_ALPHA = 0.5f
    private val rippleAlpha = RippleAlpha(
        draggedAlpha = RippleDefaults.RippleAlpha.draggedAlpha,
        focusedAlpha = RippleDefaults.RippleAlpha.focusedAlpha,
        hoveredAlpha = RippleDefaults.RippleAlpha.hoveredAlpha,
        pressedAlpha = RIPPLE_ALPHA,
    )

    @Composable
    fun defaultPrimaryColors() = ButtonDefaults.buttonColors(
        containerColor = colorScheme.primary,
        contentColor = colorScheme.onPrimary,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun defaultSecondaryColors() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = colorScheme.secondary,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun defaultTertiaryColors() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = colorScheme.secondary,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun defaultQuaternaryColors() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = colorScheme.secondary,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun defaultAdminColors() = ButtonDefaults.buttonColors(
        containerColor = adminButton,
        contentColor = Color.White,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun defaultErrorColors() = ButtonDefaults.buttonColors(
        containerColor = colorScheme.error,
        contentColor = colorScheme.onError,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun defaultSecondaryDestructiveColors() = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = GdsLocalColorScheme.current.destructiveNativeButtonText,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun customColors(containerColor: Color, contentColor: Color) = ButtonDefaults.buttonColors(
        contentColor = contentColor,
        containerColor = containerColor,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @Composable
    fun defaultFocusColors() = ButtonDefaults.buttonColors(
        containerColor = GdsLocalColorScheme.current.focusState,
        contentColor = GdsLocalColorScheme.current.focusStateContent,
        disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
        disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
    )

    @OptIn(ExperimentalMaterial3Api::class)
    fun gdsRippleConfig(colour: Color): RippleConfiguration {
        return RippleConfiguration(
            color = colour,
            rippleAlpha = rippleAlpha,
        )
    }
}
