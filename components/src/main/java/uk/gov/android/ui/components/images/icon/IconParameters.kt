package uk.gov.android.ui.components.images.icon

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Configuration property bag for the [GdsIcon].
 *
 * @param image The Vector drawable resource to use for the [Icon] Composable.
 * @param backGroundColor The color to use for the background of the [Icon] Composable. Defaults to
 * [Color.Unspecified] which is then converted to [Colors.background] within [GdsIcon].
 * @param description The String resource used for accessibility purposes to describe the [image]
 * resource.
 * @param foreGroundColor The color to use for the foreground of the [Icon] Composable. Defaults to
 * [Color.Unspecified] which is then converted to the content color for [Colors.onBackground]
 * within [GdsIcon].
 * @param modifier The additional modifiers to apply to the [GdsIcon]. Applies this modifier first,
 * then the internal [GdsIcon] modifier.
 * @param size The length and width of the internal [Icon] Composable. This assumes a 1:1 ratio
 * size for the internal [Icon]. [sizeAsDp] converts this into a [Dp] value.
 */
data class IconParameters(
    @DrawableRes
    val image: Int,
    val backGroundColor: Color = Color.Unspecified,
    @StringRes
    val description: Int? = null,
    val foreGroundColor: Color = Color.Unspecified,
    val modifier: Modifier = Modifier,
    val size: Number? = null,
    val imagePositionAtEnd: Boolean = true
) {
    fun hasSpecifiedBackgroundColor(): Boolean = backGroundColor != Color.Unspecified
    fun hasSpecifiedIconColor(): Boolean = foreGroundColor != Color.Unspecified

    fun hasSpecifiedSize(): Boolean = size != null

    /**
     * Converts [size] into a [Dp] value.
     */
    fun sizeAsDp(): Dp? = size?.toFloat()?.dp
}
