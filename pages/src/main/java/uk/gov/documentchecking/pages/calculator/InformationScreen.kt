package uk.gov.documentchecking.pages.calculator

import android.content.res.Configuration
import androidx.annotation.ArrayRes
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import uk.gov.documentchecking.pages.R as pagesR
import uk.gov.documentchecking.pages.R.string
import uk.gov.ui.components.ButtonParameters
import uk.gov.ui.components.ButtonType
import uk.gov.ui.components.GdsButton
import uk.gov.ui.components.GdsHeading
import uk.gov.ui.components.HeadingParameters
import uk.gov.ui.components.HeadingSize
import uk.gov.ui.components.content.BulletListParameters
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.content.GdsBulletList
import uk.gov.ui.components.content.GdsContent
import uk.gov.ui.components.content.GdsContentText.GdsContentTextArray
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.mediumPadding
import uk.gov.ui.theme.smallPadding
import uk.gov.ui.theme.xsmallPadding

@Suppress("LongMethod")
@Composable
fun InformationScreen(
    informationScreenParameters: InformationScreenParameters
) {
    informationScreenParameters.apply {
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
                Content(informationScreenParameters)
                Buttons(informationScreenParameters)
            }
        }
    }
}

@Composable
@Suppress("LongMethod")
internal fun Content(
    informationScreenParameters: InformationScreenParameters
) {
    informationScreenParameters.apply {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            content?.let {
                GdsContent(
                    contentParameters = ContentParameters(
                        modifier = Modifier
                            .padding(
                                end = smallPadding,
                                start = smallPadding,
                                bottom = xsmallPadding
                            ),
                        internalColumnModifier = Modifier
                            .padding(
                                bottom = xsmallPadding
                            ),
                        resource = listOf(
                            GdsContentTextArray(
                                text = content
                            )
                        ),
                        textAlign = contentAlign
                    )
                )
            }
            bulletContent?.let {
                GdsBulletList(
                    bulletListParameters = BulletListParameters(
                        contentText = GdsContentTextArray(text = bulletContent)
                    )
                )
            }
        }
    }
}

@Composable
internal fun Buttons(
    informationScreenParameters: InformationScreenParameters
) {
    informationScreenParameters.apply {
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
        }
    }
}

data class InformationScreenParameters(
    @ArrayRes
    val content: Int? = null,
    @ArrayRes
    val bulletContent: Int? = null,
    val contentAlign: TextAlign = TextAlign.Start,
    var onPrimary: () -> Unit = {},
    var onHelp: () -> Unit = {},
    @StringRes
    val primaryButtonText: Int,
    @StringRes
    val title: Int,
    val titleAlign: TextAlign = TextAlign.Start,
    val titleBottomPadding: Dp = mediumPadding
)

class InformationScreenProvider : PreviewParameterProvider<InformationScreenParameters> {
    override val values: Sequence<InformationScreenParameters> = sequenceOf(
        InformationScreenParameters(
            title = string.preview__BrpInstructions__title,
            content = pagesR.array.preview__BrpInstructions__array_1,
            bulletContent = pagesR.array.preview__BrpInstructions__array_0,
            primaryButtonText = string.preview__BrpInstructions__primary_button
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
private fun Preview(
    @PreviewParameter(InformationScreenProvider::class)
    parameters: InformationScreenParameters
) {
    InformationScreen(
        parameters
    )
}
