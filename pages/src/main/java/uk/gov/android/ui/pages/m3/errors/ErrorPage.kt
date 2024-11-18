package uk.gov.android.ui.pages.m3.errors

import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.layoutId
import uk.gov.android.ui.pages.errors.ErrorPageTags.rootLayout
import uk.gov.android.ui.pages.m3.errors.extensions.SetupInformationComponent
import uk.gov.android.ui.pages.m3.errors.extensions.SetupPrimaryButtonComponent
import uk.gov.android.ui.pages.m3.errors.extensions.SetupSecondaryButton
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

@Composable
fun ErrorPage(
    parameters: ErrorPageParameters,
    colors: ColorScheme = MaterialTheme.colorScheme
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colors.background)
            .padding(smallPadding)
            .layoutId(rootLayout)
            .testTag(rootLayout)
            .then(parameters.modifier)
    ) {
        val (informationRef, primaryButtonRef, secondaryButtonRef) = createRefs()

        SetupInformationComponent(parameters, informationRef, colors)

        SetupPrimaryButtonComponent(
            primaryButtonRef,
            informationRef,
            parameters,
            secondaryButtonRef
        )

        parameters.secondaryButtonParameters?.let {
            SetupSecondaryButton(
                it,
                secondaryButtonRef,
                primaryButtonRef
            )
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
fun ErrorPagePreview(
    @PreviewParameter(ErrorPageProvider::class)
    parameters: ErrorPageParameters
) {
    GdsTheme {
        ErrorPage(parameters)
    }
}
