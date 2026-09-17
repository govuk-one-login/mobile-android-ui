package uk.gov.android.ui.componentsv2.text.previewparameterprovider

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.text.annotatedstringparameters.GdsAnnotatedStringPreviewData
import uk.gov.android.ui.theme.m3.ExtraTypography
import uk.gov.android.ui.theme.m3.Typography

class GdsAnnotatedStringPreviewDataProvider :
    PreviewParameterProvider<GdsAnnotatedStringPreviewData> {
    override val values: Sequence<GdsAnnotatedStringPreviewData> = sequenceOf(
        GdsAnnotatedStringPreviewData(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Bold,
            icon = R.drawable.ic_external_site,
            iconContentDescription = R.string.icon_content_desc,
            isIconTrailing = true,
            iconColor = Color.Green,
        ),
        GdsAnnotatedStringPreviewData(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Bold,
            icon = R.drawable.ic_error_filled,
            iconContentDescription = R.string.icon_content_desc,
            isIconTrailing = false,
            iconColor = Color.Black,
            nightMode = true,
        ),
        GdsAnnotatedStringPreviewData(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Normal,
            icon = R.drawable.ic_external_site,
            iconContentDescription = R.string.icon_content_desc,
            textStyle = Typography.bodyLarge,
        ),
        GdsAnnotatedStringPreviewData(
            text = R.string.annotated_string,
            fontWeight = FontWeight.Bold,
            icon = R.drawable.ic_external_site,
            iconContentDescription = R.string.icon_content_desc,
            textStyle = ExtraTypography.bodyLargeBold,
            color = Color.Green,
            iconColor = Color.Green,
        ),
    )
}
