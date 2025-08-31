@file:Suppress("ForbiddenComment")

package uk.gov.android.ui.componentsv2.button

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.m3.adminButton

@Deprecated(
    message = "This is now deprecated because this version is used in a deprecated version of GdsButton - please update to new version of GdsButton",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - ButtonTypeV2"),
    level = DeprecationLevel.WARNING
)
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

@Immutable
sealed class ButtonTypeV2(
    open val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
) {
    data class Primary(
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Secondary(
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Tertiary(
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Quaternary(
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Admin(
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Error(
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Custom(
        val contentColor: Color,
        val containerColor: Color,
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Light),
    ) : ButtonTypeV2(textStyle = textStyle)

    data class Icon(
        val buttonColors: ButtonColors,
        val iconImage: ImageVector,
        val contentDescription: String,
        override val textStyle: TextStyle = Typography.bodyLarge.copy(fontWeight = FontWeight.Light),
        val isIconTrailing: Boolean = true,
        val shadowColor: Color = Color.Transparent,
    ) : ButtonTypeV2(textStyle = textStyle)
}

@Deprecated(
    message = "Remove once the GdsButton deprecated version has been removed",
    replaceWith = ReplaceWith("Not needed")
)
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

@Deprecated(
    message = "This has been replaced by ButtonTypeV2.buttonColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - ButtonTypeV2.buttonColors(")
)
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
fun ButtonTypeV2.buttonColors() = when (this) {
    is ButtonTypeV2.Admin -> GdsButtonDefaults.defaultAdminColors()
    is ButtonTypeV2.Custom -> GdsButtonDefaults.customColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    is ButtonTypeV2.Error -> GdsButtonDefaults.defaultErrorColors()
    is ButtonTypeV2.Icon -> buttonColors
    is ButtonTypeV2.Primary -> GdsButtonDefaults.defaultPrimaryColors()
    is ButtonTypeV2.Quaternary -> GdsButtonDefaults.defaultQuaternaryColors()
    is ButtonTypeV2.Secondary -> GdsButtonDefaults.defaultSecondaryColors()
    is ButtonTypeV2.Tertiary -> GdsButtonDefaults.defaultTertiaryColors()
}

object GdsButtonDefaults {
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
}

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.defaultPrimaryColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.defaultPrimaryColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun primaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.primary,
    contentColor = colorScheme.onPrimary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.defaultSecondaryColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.defaultSecondaryColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = colorScheme.secondary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.defaultTertiaryColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.defaultTertiaryColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun tertiaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = colorScheme.secondary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.defaultQuaternaryColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.defaultQuaternaryColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun quaternaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    contentColor = colorScheme.secondary,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.defaultAdminColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.defaultAdminColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun adminButtonColors() = ButtonDefaults.buttonColors(
    containerColor = adminButton,
    contentColor = Color.White,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.defaultErrorColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.defaultErrorColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun errorButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.error,
    contentColor = colorScheme.onError,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.customColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.customColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun customButtonColors(containerColor: Color, contentColor: Color) = ButtonDefaults.buttonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)

@Deprecated(
    message = "This is now deprecated, please update to using GdsButtonDefaults.defaultFocusColors()",
    replaceWith = ReplaceWith("java/uk/gov/android/ui/componentsv2/button/ButtonType.kt - GdsButtonDefaults.defaultFocusColors()"),
    level = DeprecationLevel.WARNING
)
@Composable
fun focusStateButtonColors() = ButtonDefaults.buttonColors(
    containerColor = GdsLocalColorScheme.current.focusState,
    contentColor = GdsLocalColorScheme.current.focusStateContent,
    disabledContainerColor = GdsLocalColorScheme.current.disabledButton,
    disabledContentColor = GdsLocalColorScheme.current.disabledButtonContent,
)
