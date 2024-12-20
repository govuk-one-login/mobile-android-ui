package uk.gov.android.ui.pages.errors

import androidx.compose.ui.Modifier
import uk.gov.android.ui.components.HeadingSize.H1
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonType
import uk.gov.android.ui.components.content.ContentParameters
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.information.InformationParameters

data class ErrorPageParameters(
    val primaryButtonParameters: ButtonParameters,
    val informationParameters: InformationParameters,
    val secondaryButtonParameters: ButtonParameters? = null,
    val modifier: Modifier = Modifier,
) {
    fun getSubtitleEntry(resourceIndex: Int = 0) = informationParameters.getSubtitleEntry(
        resourceIndex,
    )
    fun hasSecondaryButton(): Boolean = secondaryButtonParameters != null
}

fun generateParameters(
    model: ErrorPageResources,
    onPrimaryClick: () -> Unit = { },
    onSecondaryClick: () -> Unit = { },
) = ErrorPageParameters(
    informationParameters = InformationParameters(
        contentParameters = ContentParameters(
            headingSize = H1(),
            resource = model.information.content,
        ),
        iconParameters = IconParameters(
            image = model.information.icon.image,
            description = model.information.icon.description,
            size = 100,
        ),
    ),
    primaryButtonParameters = ButtonParameters(
        buttonType = ButtonType.PRIMARY(),
        onClick = onPrimaryClick,
        text = model.primaryButtonText,
    ),
    secondaryButtonParameters = ButtonParameters(
        buttonType = ButtonType.SECONDARY(),
        onClick = onSecondaryClick,
        text = model.secondaryButtonText!!,
    ),
)
