package uk.gov.documentchecking.pages

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import uk.gov.ui.components.GdsVectorImage
import uk.gov.ui.components.HeadingParameters
import uk.gov.ui.components.HeadingSize
import uk.gov.ui.components.VectorImageParameters
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.content.GdsContent
import uk.gov.ui.components.content.GdsContentText
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
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Content(landingPageParameters)
                    Buttons(landingPageParameters)

                }

            }
        }
    }

@Composable
@Suppress("LongMethod")
internal fun Content(
    landingPageParameters:LandingPageParameters
) {
    landingPageParameters.apply {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight(),
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

        }
    }
}


data class LandingPageParameters(
    @DrawableRes
    var topIcon: Int? = null,
    var topIconScale: ContentScale = ContentScale.FillWidth,
    val content: List<LandingPageContentSection>,
    val contentAlign: TextAlign = TextAlign.Center,
    var onPrimary: () -> Unit = {},
    @StringRes
    val primaryButtonText: Int,
    @StringRes
    val title: Int,
    val titleAlign: TextAlign = TextAlign.Center,
    val titleBottomPadding: Dp = mediumPadding,
    val subTitle: Int? = null

)

class LandingPageProvider : PreviewParameterProvider<LandingPageParameters> {
    override val values: Sequence<LandingPageParameters> = sequenceOf(
        LandingPageParameters(
            topIcon = R.drawable.ic_photo_camera,
            title = R.string.preview__BrpInstructions__title,
            content = listOf(
                LandingPageContentSection(
                    text = R.array.preview__BrpInstructions__array_0
                ),

            ),
            primaryButtonText = R.string.preview__BrpInstructions__primary_button,
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
    @PreviewParameter(LandingPageProvider::class)
    parameters: LandingPageParameters
) {
    LandingPage(
        landingPageParameters = parameters
    )
}