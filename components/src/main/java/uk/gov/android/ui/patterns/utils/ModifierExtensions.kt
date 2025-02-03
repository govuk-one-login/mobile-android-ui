package uk.gov.android.ui.patterns.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.tileCornerRadius
import uk.gov.android.ui.theme.xsmallPadding

object ModifierExtensions {
    fun Modifier.customTilePadding(apply: Boolean) = if (apply) {
        fillMaxWidth()
            .padding(top = smallPadding)
    } else {
        fillMaxWidth()
            .padding(vertical = smallPadding)
    }
}
