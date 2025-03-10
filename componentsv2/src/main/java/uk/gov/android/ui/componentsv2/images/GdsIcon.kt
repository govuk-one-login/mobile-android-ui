package uk.gov.android.ui.componentsv2.images

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.m3.GdsTheme

@Composable
fun GdsIcon(
    image: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
    size: Dp? = null,
) {
    val setColor = color.ifSpecified(default = MaterialTheme.colorScheme.onBackground)
    val setBackgroundColor =
        backgroundColor.ifSpecified(default = MaterialTheme.colorScheme.background)
    val iconModifier = modifier.then(
        Modifier
            .background(setBackgroundColor)
            .layoutId(image.toString())
            .testTag(image.toString()),
    )
    Icon(
        imageVector = image,
        contentDescription = contentDescription,
        tint = setColor,
        modifier = if (size != null) {
            Modifier
                .size(size)
                .then(iconModifier)
        } else {
            iconModifier
        },
    )
}

internal data class IconPreviewParameters(
    @DrawableRes
    val image: Int,
    val color: Color = Color.Unspecified,
    val backgroundColor: Color = Color.Unspecified,
    @StringRes
    val contentDescription: Int,
    val modifier: Modifier = Modifier,
    val size: Dp? = null,
)

@Composable
private fun Color.ifSpecified(
    default: Color,
): Color = if (this != Color.Unspecified) {
    this
} else {
    default
}

internal class IconPreviewParametersProvider : PreviewParameterProvider<IconPreviewParameters> {
    override val values: Sequence<IconPreviewParameters> = sequenceOf(
        IconPreviewParameters(
            image = R.drawable.ic_external_site,
            contentDescription = R.string.icon_content_desc,
        ),
        IconPreviewParameters(
            image = R.drawable.ic_external_site,
            contentDescription = R.string.icon_content_desc,
            color = Color.Blue,
            backgroundColor = Color.Red,
        ),
    )
}

@Composable
@PreviewLightDark
internal fun IconPreview(
    @PreviewParameter(IconPreviewParametersProvider::class)
    parameters: IconPreviewParameters,
) {
    GdsTheme {
        GdsIcon(
            image = ImageVector.vectorResource(parameters.image),
            modifier = parameters.modifier,
            color = parameters.color,
            backgroundColor = parameters.backgroundColor,
            contentDescription = stringResource(parameters.contentDescription),
            size = parameters.size,
        )
    }
}
