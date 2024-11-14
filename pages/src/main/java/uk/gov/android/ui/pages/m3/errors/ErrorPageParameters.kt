package uk.gov.android.ui.pages.m3.errors

import androidx.compose.ui.Modifier
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.HeadingSize
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonType
import uk.gov.android.ui.components.m3.content.ContentParameters
import uk.gov.android.ui.components.m3.information.InformationParameters
import uk.gov.android.ui.pages.errors.ErrorPageResources

data class ErrorPageParameters(
    val primaryButtonParameters: ButtonParameters,
    val informationParameters: InformationParameters,
    val secondaryButtonParameters: ButtonParameters? = null,
    val modifier: Modifier = Modifier
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = informationParameters.getSubtitleEntry(
        resourceIndex
    )
    fun hasSecondaryButton(): Boolean = secondaryButtonParameters != null
}

fun generateParameters(
    model: ErrorPageResources,
    onPrimaryClick: () -> Unit = { },
    onSecondaryClick: () -> Unit = { }
) = ErrorPageParameters(
    informationParameters = InformationParameters(
        contentParameters = ContentParameters(
            headingSize = HeadingSize.H1(),
            resource = model.information.content
        ),
        iconParameters = IconParameters(
            image = model.information.icon.image,
            description = model.information.icon.description,
            size = 100
        )
    ),
    primaryButtonParameters = ButtonParameters(
        buttonType = ButtonType.PRIMARY(),
        onClick = onPrimaryClick,
        text = model.primaryButtonText
    ),
    secondaryButtonParameters = ButtonParameters(
        buttonType = ButtonType.SECONDARY(),
        onClick = onSecondaryClick,
        text = model.secondaryButtonText!!
    )
)
