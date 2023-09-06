package uk.gov.documentchecking.pages.errors

import androidx.annotation.Keep
import androidx.compose.ui.Modifier
import uk.gov.ui.components.ButtonParameters
import uk.gov.ui.components.ButtonType.PRIMARY
import uk.gov.ui.components.ButtonType.SECONDARY
import uk.gov.ui.components.HeadingSize.H1
import uk.gov.ui.components.content.ContentParameters
import uk.gov.ui.components.images.icon.IconParameters
import uk.gov.ui.components.information.InformationParameters

@Keep
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
            headingSize = H1(),
            resource = model.information.content
        ),
        iconParameters = IconParameters(
            image = model.information.icon.image,
            description = model.information.icon.description,
            size = 100
        )
    ),
    primaryButtonParameters = ButtonParameters(
        buttonType = PRIMARY(),
        onClick = onPrimaryClick,
        text = model.primaryButtonText
    ),
    secondaryButtonParameters = ButtonParameters(
        buttonType = SECONDARY(),
        onClick = onSecondaryClick,
        text = model.secondaryButtonText!!
    )
)
