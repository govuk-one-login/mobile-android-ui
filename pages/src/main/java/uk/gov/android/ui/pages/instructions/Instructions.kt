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
import uk.gov.android.ui.components.m3.ButtonParametersM3
import uk.gov.android.ui.components.m3.ButtonTypeM3
import uk.gov.android.ui.components.m3.GdsButtonM3
import uk.gov.android.ui.components.GdsHelpText
import uk.gov.android.ui.components.GdsVectorImage
import uk.gov.android.ui.components.m3.HeadingSizeM3
import uk.gov.android.ui.components.HelpTextParameters
import uk.gov.android.ui.components.VectorImageParameters
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.content.GdsContent
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.HeadingParametersM3
import uk.gov.android.ui.pages.R
import uk.gov.android.ui.pages.brp.BrpInstructionsContentSection
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.mediumPadding
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.xsmallPadding

sealed class Instructions {
    abstract val generate: @Composable () -> Unit
}

data class InstructionsParametersM3(
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
    val buttonParameters: List<ButtonParametersM3>? = null
) : Instructions() {
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
                    Content(instructionsParameters = this@InstructionsParametersM3)
                    Buttons(instructionsParameters = this@InstructionsParametersM3)
                }
            }
        }
}

@Composable
@Suppress("LongMethod")
internal fun Content(
    instructionsParameters: InstructionsParametersM3
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
            HeadingParametersM3(
                modifier = Modifier
                    .fillMaxWidth(),
                size = HeadingSizeM3.H1(),
                text = title,
                textAlign = titleAlign,
                padding = PaddingValues(
                    end = smallPadding,
                    start = smallPadding,
                    bottom = titleBottomPadding
                )
            ).generate()
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
    instructionsParameters: InstructionsParametersM3
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
                GdsButtonM3(parameters)
            }
        }
    }
}

class InstructionsM3Provider : PreviewParameterProvider<InstructionsParametersM3> {
    override val values: Sequence<InstructionsParametersM3> = sequenceOf(
        InstructionsParametersM3(
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
                ButtonParametersM3(
                    buttonType = ButtonTypeM3.PRIMARY(),
                    text = R.string.preview__BrpInstructions__primary_button,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = xsmallPadding),
                    onClick = {}
                ),
                ButtonParametersM3(
                    buttonType = ButtonTypeM3.ICON(
                        buttonType = ButtonTypeM3.SECONDARY(),
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
    @PreviewParameter(InstructionsM3Provider::class)
    parameters: InstructionsParametersM3
) {
    parameters.generate()
}
