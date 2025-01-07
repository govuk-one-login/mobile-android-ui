package uk.gov.android.ui.components.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.tileCornerRadius
import uk.gov.android.ui.theme.xsmallPadding

object ModifierExtensions {
    fun Modifier.elevatedCardModifier(modifier: Modifier? = null) = fillMaxWidth()
        .shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(tileCornerRadius),
        )
        .then(modifier ?: Modifier)

    fun Modifier.customTilePadding(item: Int? = null) = if (item != null) {
        fillMaxWidth()
            .padding(top = xsmallPadding)
    } else {
        fillMaxWidth()
            .padding(vertical = xsmallPadding)
    }
}
