package uk.gov.android.ui.testwrapper.componentsv2.button.secondary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.SecondaryOutlinedButton
import uk.gov.android.ui.theme.smallPadding

@Composable
fun SecondaryOutlinedButtonDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(smallPadding),
        verticalArrangement = Arrangement.spacedBy(smallPadding),
    ) {
        SecondaryOutlinedButton(
            text = R.string.secondary_button,
            onClick = {},
        )
        SecondaryOutlinedButton(
            text = R.string.secondary_button,
            onClick = {},
            icon = ImageVector.vectorResource(R.drawable.ic_external_site),
            iconContentDescription = null,
            isIconTrailing = false,
        )
        SecondaryOutlinedButton(
            text = R.string.secondary_button,
            onClick = {},
            icon = ImageVector.vectorResource(R.drawable.ic_external_site),
            iconContentDescription = null,
            isIconTrailing = true,
            borderWidth = 4.dp,
        )
        SecondaryOutlinedButton(
            text = R.string.secondary_button,
            onClick = {},
            enabled = false,
        )
    }
}
