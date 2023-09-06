package uk.gov.documentchecking.pages.brp

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
import uk.gov.documentchecking.pages.R as pagesR
import uk.gov.documentchecking.pages.R.drawable
import uk.gov.documentchecking.pages.R.string
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
import uk.gov.ui.components.content.GdsContentText.GdsContentTextArray
import uk.gov.ui.components.images.icon.IconParameters
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.mediumPadding
import uk.gov.ui.theme.smallPadding
import uk.gov.ui.theme.xsmallPadding

@Suppress("LongMethod")
@Composable
fun BrpInstructions(
    brpInstructionsParameters: BrpInstructionsParameters
) {
    brpInstructionsParameters.apply {
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
                Content(brpInstructionsParameters)
                Buttons(brpInstructionsParameters)
            }
        }
    }
}

@Composable
@Suppress("LongMethod")
internal fun Content(
    brpInstructionsParameters: BrpInstructionsParameters
) {
    brpInstructionsParameters.apply {
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
                        GdsContentTextArray(
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
    brpInstructionsParameters: BrpInstructionsParameters
) {
    brpInstructionsParameters.apply {
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
                            image = drawable.ic_external_site,
                            description = string.externalSite
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

data class BrpInstructionsParameters(
    @DrawableRes
    var topIcon: Int? = null,
    var topIconScale: ContentScale = ContentScale.Fit,
    val content: List<BrpInstructionsContentSection>,
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
    val helpTextParameters: HelpTextParameters? = null
)

class BrpInstructionsProvider : PreviewParameterProvider<BrpInstructionsParameters> {
    override val values: Sequence<BrpInstructionsParameters> = sequenceOf(
        BrpInstructionsParameters(
            topIcon = drawable.ic_photo_camera,
            title = string.preview__BrpInstructions__title,
            content = listOf(
                BrpInstructionsContentSection(
                    subTitle = string.preview__BrpInstructions__subtitle_1,
                    text = pagesR.array.preview__BrpInstructions__array_0
                ),
                BrpInstructionsContentSection(
                    subTitle = string.preview__BrpInstructions__subtitle_2,
                    text = pagesR.array.preview__BrpInstructions__array_1
                )
            ),
            image = drawable.preview__brpinstructions,
            primaryButtonText = string.preview__BrpInstructions__primary_button,
            secondaryButtonText = string.preview__BrpInstructions__secondary_button,
            helpTextParameters = HelpTextParameters(
                text = string.preview__BrpInstructions__help_text,
                iconParameters = IconParameters(
                    image = drawable.ic_warning_icon
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
    @PreviewParameter(BrpInstructionsProvider::class)
    parameters: BrpInstructionsParameters
) {
    BrpInstructions(
        brpInstructionsParameters = parameters
    )
}
