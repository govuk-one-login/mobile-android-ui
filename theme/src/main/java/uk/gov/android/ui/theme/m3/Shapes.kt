package uk.gov.android.ui.theme.m3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * [Material3 shape styles](https://developer.android.com/develop/ui/compose/designsystems/material2-material3#shape)
 * [Baseline List of what components use what shape](https://m3.material.io/styles/shape/shape-scale-tokens#b09934f1-1b0f-4ce4-ade6-4a1f138add6c)
 *
 * */
val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp),
    extraLarge = RoundedCornerShape(0.dp),
)

internal val shapeDisplayMap = mapOf(
    "none" to RectangleShape,
    "extraSmall" to Shapes.extraSmall,
    "small" to Shapes.small,
    "medium" to Shapes.medium,
    "large" to Shapes.large,
    "extraLarge" to Shapes.extraLarge,
    "full" to CircleShape,
)

@Preview
@Composable
internal fun ShapesPreview() {
    GdsTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            shapeDisplayMap.forEach { (label, shape) ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(100.dp)
                        .clip(shape)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                        ),
                ) {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}
