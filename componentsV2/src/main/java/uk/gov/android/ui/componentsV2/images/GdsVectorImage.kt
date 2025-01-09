package uk.gov.android.ui.componentsV2.images

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.theme.GdsTheme

@Composable
fun GdsVectorImage(
    parameters: VectorImageParameters,
) {
    parameters.apply {
        Image(
            painter = key(image) { painterResource(id = image) },
            colorFilter = this.hasSpecifiedColor(),
            contentDescription = stringResource(id = contentDescription),
            contentScale = scale,
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
        )
    }
}

data class VectorImageParameters(
    @DrawableRes
    val image: Int,
    val color: Color = Color.Unspecified,
    @StringRes
    val contentDescription: Int,
    val scale: ContentScale = ContentScale.Fit,
    val modifier: Modifier = Modifier,
) {
    fun hasSpecifiedColor(): ColorFilter? = if (color != Color.Unspecified) {
        ColorFilter.tint(color)
    } else {
        null
    }
}

class VectorImageProvider : PreviewParameterProvider<VectorImageParameters> {
    override val values: Sequence<VectorImageParameters> = sequenceOf(
        VectorImageParameters(
            contentDescription = R.string.vector_image_content_description,
            image = R.drawable.ic_vector_image,
            color = Color.Black,
        ),
        VectorImageParameters(
            contentDescription = R.string.vector_image_content_description,
            image = R.drawable.ic_vector_image,
        ),
    )
}

@Composable
@PreviewLightDark
fun VectorImagePreview(
    @PreviewParameter(VectorImageProvider::class)
    parameters: VectorImageParameters,
) {
    GdsTheme {
        GdsVectorImage(parameters)
    }
}
