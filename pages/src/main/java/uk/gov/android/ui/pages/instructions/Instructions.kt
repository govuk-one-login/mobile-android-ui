package uk.gov.android.ui.pages.instructions

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
import uk.gov.android.ui.components.GdsHeading
import uk.gov.android.ui.components.GdsHelpText
import uk.gov.android.ui.components.GdsVectorImage
import uk.gov.android.ui.components.HeadingParameters
import uk.gov.android.ui.components.HeadingSize
import uk.gov.android.ui.components.HelpTextParameters
import uk.gov.android.ui.components.VectorImageParameters
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonType
import uk.gov.android.ui.components.buttons.GdsButton
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.content.GdsContent
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.pages.R
import uk.gov.android.ui.pages.brp.BrpInstructionsContentSection
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

data class Instructions(
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
    var onHelp: () -> Unit = {},
    @StringRes
    val title: Int,
    val titleAlign: TextAlign = TextAlign.Start,
    val titleBottomPadding: Dp = mediumPadding,
    val helpTextParameters: HelpTextParameters? = null,
    val buttonParameters: List<ButtonParameters>? = null
) : BaseInstructions() {
    override val generate: @Composable () -> Unit
        get() = {
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
                    Content(instructionsParameters = this@Instructions)
                    Buttons(instructionsParameters = this@Instructions)
                }
            }
        }
}

@Composable
@Suppress("LongMethod")
internal fun Content(
    instructionsParameters: Instructions
) {
    instructionsParameters.apply {
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
    instructionsParameters: Instructions
) {
    instructionsParameters.buttonParameters?.let {
        Column(
            modifier = Modifier
                .padding(
                    end = smallPadding,
                    start = smallPadding,
                    top = mediumPadding
                )
        ) {
            it.forEach { parameters ->
                GdsButton(parameters)
            }
        }
    }
}

class InstructionsProvider : PreviewParameterProvider<Instructions> {
    override val values: Sequence<Instructions> = sequenceOf(
        Instructions(
            topIcon = R.drawable.ic_photo_camera,
            title = R.string.preview__BrpInstructions__title,
            content = listOf(
                BrpInstructionsContentSection(
                    subTitle = R.string.preview__BrpInstructions__subtitle_1,
                    text = R.array.preview__BrpInstructions__array_0
                ),
                BrpInstructionsContentSection(
                    subTitle = R.string.preview__BrpInstructions__subtitle_2,
                    text = R.array.preview__BrpInstructions__array_1
                )
            ),
            image = R.drawable.preview__brpinstructions,
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
            ),
            buttonParameters = listOf(
                ButtonParameters(
                    buttonType = ButtonType.PRIMARY(),
                    text = R.string.preview__BrpInstructions__primary_button,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = xsmallPadding),
                    onClick = {}
                ),
                ButtonParameters(
                    buttonType = ButtonType.ICON(
                        buttonType = ButtonType.SECONDARY(),
                        iconParameters = IconParameters(
                            image = R.drawable.ic_external_site,
                            description = R.string.externalSite
                        )
                    ),
                    text = R.string.preview__BrpInstructions__secondary_button,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {}
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
    @PreviewParameter(InstructionsProvider::class)
    parameters: Instructions
) {
    parameters.generate()
}
