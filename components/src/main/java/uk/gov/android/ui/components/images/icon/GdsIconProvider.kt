package uk.gov.android.ui.components.images.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R.drawable

/**
 * Collection of [IconParameters] for use within Android Studio previews.
 */
class GdsIconProvider : PreviewParameterProvider<IconParameters> {
    override val values: Sequence<IconParameters> = sequenceOf(
        IconParameters(
            image = drawable.ic_error
        ),
        IconParameters(
            backGroundColor = Color.Green,
            image = drawable.ic_error,
            size = 100
        ),
        IconParameters(
            foreGroundColor = Color.Green,
            image = drawable.ic_error,
            size = 100
        )
    )
}
