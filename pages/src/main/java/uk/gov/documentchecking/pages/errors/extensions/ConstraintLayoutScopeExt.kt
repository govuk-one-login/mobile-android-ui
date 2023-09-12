package uk.gov.documentchecking.pages.errors.extensions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.layoutId
import uk.gov.documentchecking.pages.errors.ErrorPageParameters
import uk.gov.documentchecking.pages.errors.ErrorPageTags
import uk.gov.ui.components.ButtonParameters
import uk.gov.ui.components.GdsButton
import uk.gov.ui.components.information.Information
import uk.gov.ui.theme.xsmallPadding

@Composable
internal fun ConstraintLayoutScope.SetupSecondaryButton(
    parameters: ButtonParameters,
    secondaryButtonRef: ConstrainedLayoutReference,
    primaryButtonRef: ConstrainedLayoutReference,
    colors: Colors
) {
    key(parameters) {
        val buttonModifier = Modifier
            .constrainAs(secondaryButtonRef) {
                this.end.linkTo(parent.end)
                this.start.linkTo(parent.start)
                this.top.linkTo(primaryButtonRef.bottom)
                linkTo(
                    secondaryButtonRef.bottom,
                    parent.bottom,
                    bias = 1.0f
                )
            }
            .padding(top = xsmallPadding)
            .layoutId(ErrorPageTags.secondaryButton)
            .testTag(ErrorPageTags.secondaryButton)
            .then(parameters.modifier)

        GdsButton(
            buttonParameters = parameters.copy(
                modifier = Modifier.fillMaxWidth().then(buttonModifier)
            ),
            colors = colors
        )
    }
}

@Composable
internal fun ConstraintLayoutScope.SetupPrimaryButtonComponent(
    primaryButtonRef: ConstrainedLayoutReference,
    informationRef: ConstrainedLayoutReference,
    parameters: ErrorPageParameters,
    secondaryButtonRef: ConstrainedLayoutReference,
    colors: Colors
) {
    val primaryButtonModifier = Modifier
        .constrainAs(primaryButtonRef) {
            this.end.linkTo(parent.end)
            this.start.linkTo(parent.start)
            this.top.linkTo(informationRef.bottom)

            val bottomLink = if (parameters.hasSecondaryButton()) {
                secondaryButtonRef.top
            } else {
                parent.bottom
            }

            linkTo(primaryButtonRef.bottom, bottomLink, bias = 1.0f)
        }
        .fillMaxWidth()
        .layoutId(ErrorPageTags.primaryButton)
        .testTag(ErrorPageTags.primaryButton)
        .then(parameters.primaryButtonParameters.modifier)

    GdsButton(
        buttonParameters = parameters.primaryButtonParameters.copy(
            modifier = primaryButtonModifier
        ),
        colors = colors
    )
}

@Composable
internal fun ConstraintLayoutScope.SetupInformationComponent(
    parameters: ErrorPageParameters,
    informationRef: ConstrainedLayoutReference,
    colors: Colors
) {
    key(parameters.informationParameters) {
        val informationModifier = Modifier
            .constrainAs(informationRef) {
                this.bottom.linkTo(parent.bottom)
                this.end.linkTo(parent.end)
                this.start.linkTo(parent.start)
                this.top.linkTo(parent.top)
            }
            .layoutId(ErrorPageTags.information)
            .testTag(ErrorPageTags.information)
            .then(parameters.informationParameters.modifier)

        Information(
            parameters = parameters.informationParameters.copy(
                modifier = informationModifier
            ),
            colors = colors
        )
    }
}
