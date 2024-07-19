package uk.gov.android.ui.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.mediumPadding

@Composable
fun GdsVectorImage(
    parameters: VectorImageParameters
) {
    parameters.apply {
        val filter = if (parameters.hasSpecifiedColor()) {
            ColorFilter.tint(parameters.iconColor)
        } else {
            null
        }

        Image(
            colorFilter = filter,
            contentDescription = description?.let {
                stringResource(id = description)
            },
            contentScale = scale,
            modifier = modifier.testTag(
                image.toString()
            ),
            painter = key(image) { painterResource(id = image) }
        )
    }
}

data class VectorImageParameters(
    val iconColor: Color = Color.Unspecified,
    @StringRes
    val description: Int? = null,
    @DrawableRes
    val image: Int,
    val modifier: Modifier = Modifier.fillMaxWidth(),
    val scale: ContentScale = ContentScale.Fit
) {
    fun hasSpecifiedColor(): Boolean = iconColor != Color.Unspecified
}

class VectorImageProvider : PreviewParameterProvider<VectorImageParameters> {
    override val values: Sequence<VectorImageParameters> = sequenceOf(
        VectorImageParameters(
            description = R.string.preview__GdsVectorImage__description,
            image = R.drawable.ic_error,
            iconColor = Color.Black
        ),
        VectorImageParameters(
            description = R.string.preview__GdsVectorImage__description,
            image = R.drawable.preview__gdsvectorimage
        ),
        VectorImageParameters(
            description = R.string.preview__GdsVectorImage__description,
            image = R.drawable.preview__gdsvectorimage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = mediumPadding)
        ),
        VectorImageParameters(
            description = R.string.preview__GdsVectorImage__description,
            image = R.drawable.preview__gdsvectorimage,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = mediumPadding),
            scale = ContentScale.Crop
        ),
        VectorImageParameters(
            description = R.string.preview__GdsVectorImage__description,
            image = R.drawable.preview__gdsvectorimage,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = mediumPadding),
            scale = ContentScale.FillHeight
        ),
        VectorImageParameters(
            description = R.string.preview__GdsVectorImage__description,
            image = R.drawable.preview__gdsvectorimage,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = mediumPadding),
            scale = ContentScale.Fit
        )
    )
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun VectorImagePreview(
    @PreviewParameter(VectorImageProvider::class)
    vectorImageParameters: VectorImageParameters
) {
    GdsTheme {
        Column(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .fillMaxSize()
        ) {
            GdsVectorImage(
                vectorImageParameters
            )
        }
    }
}
