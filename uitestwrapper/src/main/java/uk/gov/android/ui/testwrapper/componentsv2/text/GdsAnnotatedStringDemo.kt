package uk.gov.android.ui.testwrapper.componentsv2.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.text.GdsAnnotatedString
import uk.gov.android.ui.testwrapper.DemoTemplate
import uk.gov.android.ui.theme.m3.ExtraTypography

@Composable
fun GdsAnnotatedStringDemo(modifier: Modifier = Modifier) {
    DemoTemplate {
        GdsAnnotatedString(
            modifier = modifier,
            text = "Annotated String",
            fontWeight = FontWeight.Normal,
            textStyle = ExtraTypography.bodyLargeBold,
            icon = ImageVector.vectorResource(R.drawable.ic_external_site),
            iconContentDescription = "External site link",
        )
    }
}
