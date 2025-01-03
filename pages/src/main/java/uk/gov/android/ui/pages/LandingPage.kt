package uk.gov.android.ui.pages

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import uk.gov.android.ui.components.GdsHeading
import uk.gov.android.ui.components.GdsVectorImage
import uk.gov.android.ui.components.HeadingParameters
import uk.gov.android.ui.components.HeadingSize
import uk.gov.android.ui.components.VectorImageParameters
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonType
import uk.gov.android.ui.components.buttons.GdsButton
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.content.GdsContent
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding

@Suppress("LongMethod")
@Composable
fun LandingPage(
    landingPageParameters: LandingPageParameters,
) {
    landingPageParameters.apply {
        GdsTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = mediumPadding,
                        top = mediumPadding,
                    ),
                verticalArrangement = Arrangement.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f),
                ) {
                    topIcon?.let {
                        GdsVectorImage(
                            VectorImageParameters(
                                modifier = Modifier
                                    .padding(paddingValues = iconPadding)
                                    .clickable(enabled = onTopIconClick != null) {
                                        onTopIconClick?.invoke()
                                    },
                                description = contentDescription,
                                image = it,
                                scale = topIconScale,
                                iconColor = iconColor,
                            ),
                        )
                    }
                    GdsHeading(
                        headingParameters = HeadingParameters(
                            modifier = Modifier
                                .fillMaxWidth(),
                            size = HeadingSize.H1(),
                            text = title,
                            textAlign = titleAlign,
                            padding = PaddingValues(
                                end = smallPadding,
                                start = smallPadding,
                                bottom = titleBottomPadding,
                            ),
                        ),
                    )
                    content?.let {
                        GdsContent(
                            contentParameters = ContentParameters(
                                modifier = Modifier
                                    .padding(contentPadding),
                                internalColumnModifier = Modifier
                                    .padding(contentInternalPadding),
                                resource = it,
                                textAlign = contentAlign,
                            ),
                        )
                    }
                }
                GdsButton(
                    buttonParameters = ButtonParameters(
                        buttonType = ButtonType.PRIMARY(),
                        text = primaryButtonText,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = smallPadding,
                                end = smallPadding,
                            ),
                        onClick = onPrimary,
                    ),
                )
            }
        }
    }
}

data class LandingPageParameters(
    @DrawableRes
    var topIcon: Int? = null,
    var topIconScale: ContentScale = ContentScale.FillWidth,
    val iconColor: Color = Color.Unspecified,
    var contentDescription: Int? = null,
    val iconPadding: PaddingValues = PaddingValues(bottom = mediumPadding),
    val onTopIconClick: (() -> Unit)? = null,
    val content: List<GdsContentText>? = null,
    val contentAlign: TextAlign = TextAlign.Center,
    val contentInternalPadding: PaddingValues = PaddingValues(bottom = smallPadding),
    val contentPadding: PaddingValues = PaddingValues(
        end = smallPadding,
        start = smallPadding,
        bottom = smallPadding,
    ),
    var onPrimary: () -> Unit = {},
    @StringRes
    val primaryButtonText: Int,
    @StringRes
    val title: Int,
    val titleAlign: TextAlign = TextAlign.Center,
    val titleBottomPadding: Dp = mediumPadding,
)

class LandingPageProvider : PreviewParameterProvider<LandingPageParameters> {
    override val values: Sequence<LandingPageParameters> = sequenceOf(
        LandingPageParameters(
            topIcon = R.drawable.ic_photo_camera,
            contentDescription = R.string.preview__landingPage__iconContentDescription,
            title = R.string.preview__BrpInstructions__title,
            content = listOf(
                GdsContentText.GdsContentTextString(
                    intArrayOf(R.string.preview__BrpInstructions__subtitle_1),
                ),
            ),
            primaryButtonText = R.string.preview__BrpInstructions__primary_button,
        ),
        LandingPageParameters(
            topIcon = R.drawable.ic_photo_camera,
            iconPadding = PaddingValues(bottom = smallPadding),
            contentDescription = R.string.preview__landingPage__iconContentDescription,
            title = R.string.preview__BrpInstructions__title,
            primaryButtonText = R.string.preview__BrpInstructions__primary_button,
        ),
    )
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    backgroundColor = 0xFF000000,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun Preview(
    @PreviewParameter(LandingPageProvider::class)
    parameters: LandingPageParameters,
) {
    LandingPage(
        landingPageParameters = parameters,
    )
}
