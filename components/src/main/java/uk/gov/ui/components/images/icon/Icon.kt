package uk.gov.ui.components.images.icon

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.compose.layoutId
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.smallPadding

/**
 * Composable for showing an [Icon] to the User.
 *
 * This is for when having the option to change the color of the image is useful. For images that
 * don't change their color, a [uk.gov.ui.components.GdsVectorImage] may be more appropriate, due
 * to how the [androidx.compose.foundation.Image] performs tinting instead of color overrides.
 *
 * @param parameters The configuration of this Composable.
 * @param colors The theme colors to override. Defaults to [MaterialTheme.colors] due to how the
 * [GdsTheme] handles coloring.
 *
 * @see uk.gov.ui.components.GdsVectorImage
]
 */
@Composable
fun GdsIcon(
    parameters: IconParameters,
    colors: Colors = MaterialTheme.colors
) {
    parameters.apply {
        key(this) {
            val colorForIcon = if (hasSpecifiedIconColor()) {
                parameters.foreGroundColor
            } else {
                colors.onBackground
            }

            val colorForBackground = if (hasSpecifiedBackgroundColor()) {
                parameters.backGroundColor
            } else {
                colors.background
            }

            var iconModifier = this.modifier.then(
                Modifier
                    .background(colorForBackground)
                    .layoutId(image.toString())
                    .testTag(image.toString())
            )

            if (hasSpecifiedSize()) {
                iconModifier = Modifier.size(sizeAsDp()!!).then(iconModifier)
            }

            Icon(
                painter = painterResource(id = this.image),
                contentDescription = this.description?.let { stringResource(id = it) },
                modifier = iconModifier,
                tint = colorForIcon
            )
        }
    }
}

/**
 * Generates the Android Studio preview for [GdsIcon].
 */
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun GdsIconPreview(
    @PreviewParameter(GdsIconProvider::class)
    parameters: IconParameters
) {
    val modifier = if (parameters.hasSpecifiedSize()) {
        val sizing = parameters.sizeAsDp()!!
        Modifier.height(sizing).width(sizing)
    } else {
        Modifier.padding(smallPadding)
    }

    GdsTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .then(modifier)
        ) {
            GdsIcon(
                parameters
            )
        }
    }
}
