package uk.gov.documentchecking.pages

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import uk.gov.ui.components.ButtonParameters
import uk.gov.ui.components.ButtonType
import uk.gov.ui.components.GdsButton
import uk.gov.ui.components.GdsHeading
import uk.gov.ui.components.GdsHelpText
import uk.gov.ui.components.GdsVectorImage
import uk.gov.ui.components.HeadingParameters
import uk.gov.ui.components.HeadingSize
import uk.gov.ui.components.HelpTextParameters
import uk.gov.ui.components.VectorImageParameters
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.content.GdsContent
import uk.gov.ui.components.content.GdsContentText
import uk.gov.ui.components.images.icon.IconParameters
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.mediumPadding
import uk.gov.ui.theme.smallPadding
import uk.gov.ui.theme.xsmallPadding

@Composable
    fun LandingPage(
        landingPageParameters:LandingPageParameters
    ) {
    landingPageParameters.apply {
            GdsTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(
                            bottom = mediumPadding,
                            top = mediumPadding
                        ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Content(landingPageParameters)
                    Buttons(landingPageParameters)
                }
            }
        }
    }

class LandingPageProvider : PreviewParameterProvider<LandingPageParameters> {
    override val values: Sequence<LandingPageParameters> = sequenceOf(
        LandingPageParameters(
            topIcon = R.drawable.ic_photo_camera,
            title = R.string.preview__BrpInstructions__title,
            content = listOf(
                LandingPageContentSection(
                    subTitle = R.string.preview__BrpInstructions__subtitle_1,
                    text = R.array.preview__BrpInstructions__array_0
                ),
                LandingPageContentSection(
                    subTitle = R.string.preview__BrpInstructions__subtitle_2,
                    text = R.array.preview__BrpInstructions__array_1
                )
            ),
            image = R.drawable.preview__brpinstructions,
            primaryButtonText = R.string.preview__BrpInstructions__primary_button,
            secondaryButtonText = R.string.preview__BrpInstructions__secondary_button,
            helpTextParameters = HelpTextParameters(
                text = R.string.preview__BrpInstructions__help_text,
                iconParameters = IconParameters(
                    image = R.drawable.ic_warning_icon
                ),
                rowModifier = Modifier.padding(
                    end = smallPadding,
                    start = smallPadding,
                    top = smallPadding
                )
            )
        )
    )
}

@Composable
@Suppress("LongMethod")
internal fun Content(
    landingPageParameters:LandingPageParameters
) {
    landingPageParameters.apply {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            topIcon?.let {
                GdsVectorImage(
                    VectorImageParameters(
                        modifier = Modifier.padding(bottom = mediumPadding),
                        image = it,
                        scale = topIconScale
                    )
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
                        bottom = titleBottomPadding
                    )
                )
            )
            GdsContent(
                contentParameters = ContentParameters(
                    modifier = Modifier
                        .padding(
                            end = smallPadding,
                            start = smallPadding,
                            bottom = smallPadding
                        ),
                    internalColumnModifier = Modifier
                        .padding(
                            bottom = mediumPadding
                        ),
                    resource = content.map {
                        GdsContentText.GdsContentTextArray(
                            subTitle = it.subTitle,
                            text = it.text
                        )
                    },
                    textAlign = contentAlign
                )
            )
            image?.let {
                GdsVectorImage(
                    VectorImageParameters(
                        description = imageDescription,
                        image = it,
                        scale = imageScale
                    )
                )
            }
            helpTextParameters?.let {
                GdsHelpText(it)
            }
        }
    }
}

@Composable
internal fun Buttons(
    landingPageParameters:LandingPageParameters
) {
    landingPageParameters.apply {
        Column(
            modifier = Modifier
                .padding(
                    end = smallPadding,
                    start = smallPadding,
                    top = mediumPadding
                )
        ) {
            GdsButton(
                buttonParameters = ButtonParameters(
                    buttonType = ButtonType.PRIMARY(),
                    text = primaryButtonText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = xsmallPadding),
                    onClick = onPrimary
                )
            )
            GdsButton(
                buttonParameters = ButtonParameters(
                    buttonType = ButtonType.ICON(
                        buttonType = ButtonType.SECONDARY(),
                        iconParameters = IconParameters(
                            image = R.drawable.ic_external_site,
                            description = R.string.externalSite
                        )
                    ),
                    text = secondaryButtonText,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = onSecondary
                )
            )
        }
    }
}


data class LandingPageParameters(
    @DrawableRes
    var topIcon: Int? = null,
    var topIconScale: ContentScale = ContentScale.Fit,
    val content: List<LandingPageContentSection>,
    val contentAlign: TextAlign = TextAlign.Start,
    @DrawableRes
    var image: Int? = null,
    var imageScale: ContentScale = ContentScale.FillWidth,
    @StringRes
    var imageDescription: Int? = null,
    var onPrimary: () -> Unit = {},
    var onSecondary: () -> Unit = {},
    var onHelp: () -> Unit = {},
    @StringRes
    val primaryButtonText: Int,
    @StringRes
    val secondaryButtonText: Int,
    @StringRes
    val title: Int,
    val titleAlign: TextAlign = TextAlign.Start,
    val titleBottomPadding: Dp = mediumPadding,
    val helpTextParameters: HelpTextParameters? = null,
    val subTitle: Int? = null

)

@Preview(
    backgroundColor = 0xFFFBFDF8,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    backgroundColor = 0xFF000000,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Preview(
    @PreviewParameter(LandingPageProvider::class)
    parameters: LandingPageParameters
) {
    LandingPage(
        landingPageParameters = parameters
    )
}