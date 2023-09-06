package uk.gov.ui.components.information

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import uk.gov.ui.components.content.GdsContent
import uk.gov.ui.components.images.icon.GdsIcon
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.smallPadding

/**
 * DCMAW-5886
 */
@Composable
fun Information(
    parameters: InformationParameters,
    colors: Colors = MaterialTheme.colors
) {
    ConstraintLayout(
        modifier = parameters.modifier.then(
            Modifier.background(
                colors.background
            ).semantics(mergeDescendants = true) {}
                .layoutId(InformationTags.rootLayout)
                .testTag(InformationTags.rootLayout)
        )
    ) {
        val (contentRef, imageRef) = createRefs()

        parameters.iconParameters.apply {
            key(this) {
                GdsIcon(
                    this.copy(
                        modifier = Modifier.constrainAs(imageRef) {
                            bottom.linkTo(contentRef.top)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                            .then(parameters.iconParameters.modifier)
                    ),
                    colors
                )
            }
        }

        parameters.contentParameters.apply {
            key(this) {
                val contentParams = this.copy(
                    modifier = parameters.contentParameters.modifier.then(
                        Modifier
                            .fillMaxWidth()
                            .constrainAs(contentRef) {
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                                start.linkTo(parent.start)
                                top.linkTo(
                                    imageRef.bottom,
                                    margin = parameters.dpMarginBetweenIconAndContent.toFloat().dp
                                )
                            }
                            .layoutId(InformationTags.content)
                            .testTag(InformationTags.content)
                    ),
                    headingModifier = Modifier
                        .padding(bottom = smallPadding)
                        .then(headingModifier)
                )
                GdsContent(contentParams, colors)
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun InformationPreview(
    @PreviewParameter(InformationProvider::class)
    informationParameters: InformationParameters
) {
    GdsTheme {
        Information(
            informationParameters
        )
    }
}
