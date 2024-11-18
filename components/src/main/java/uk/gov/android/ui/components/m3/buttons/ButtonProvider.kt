package uk.gov.android.ui.components.m3.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.theme.smallPadding

class ButtonProvider : PreviewParameterProvider<List<ButtonParameters>> {
    override val values: Sequence<List<ButtonParameters>> = types.asSequence()
        .map(::createButtonList)

    private fun createButtonList(type: ButtonType): List<ButtonParameters> = listOf(
        create(type),
        create(type, false),
        create(ButtonType.ICON(type, defaultIconParameters.copy())),
        create(
            ButtonType.ICON(
                parentButtonType = type,
                iconParameters = defaultIconParameters.copy(imagePositionAtEnd = false)
            )
        ),
        create(ButtonType.ICON(type, defaultIconParameters.copy()), false)
    )

    private fun create(type: ButtonType, isEnabled: Boolean = true) = ButtonParameters(
        buttonType = type,
        text = when (type) {
            is ButtonType.ICON -> type.parentButtonType.javaClass.simpleName
            else -> type.javaClass.simpleName
        },
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(smallPadding),
        isEnabled = isEnabled
    )

    companion object {
        private val types = listOf(
            ButtonType.PRIMARY(),
            ButtonType.SECONDARY(),
            ButtonType.TERTIARY(),
            ButtonType.QUATERNARY(),
            ButtonType.ADMIN(),
            ButtonType.ERROR()
        )
        private val defaultIconParameters = IconParameters(
            image = R.drawable.ic_external_site,
            description = R.string.externalSite,
            backGroundColor = Color.Transparent
        )
    }
}
