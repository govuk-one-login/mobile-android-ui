package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.theme.xsmallPadding

@RunWith(Parameterized::class)
internal class GdsButtonSecondaryOutlinedScaledIconScreenshotTest(
    private val parameters: Pair<ButtonParametersV2, Float>,
) : BaseScreenshotTest(
    fontScale = parameters.second,

) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(xsmallPadding),
                contentAlignment = Alignment.Center,
            ) {
                GdsButton(
                    text = parameters.text,
                    buttonType = parameters.buttonType.toButtonTypeV2(),
                    icon = parameters.icon?.toButtonIcon(),
                    modifier = parameters.modifier,
                    onClick = {},
                )
            }
        }
    }

    companion object {
        private const val FONT_SCALE_SMALL = 0.5f
        private const val FONT_SCALE_DEFAULT = 1.0f
        private const val FONT_SCALE_LARGE = 1.5f
        private const val FONT_SCALE_LARGEST = 2.0f

        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsButtonSecondaryOutlinedScaledIcon")
        fun values(): List<Pair<ButtonParametersV2, Float>> = listOf(
            Pair(defaultParameters, FONT_SCALE_SMALL),
            Pair(defaultParameters, FONT_SCALE_DEFAULT),
            Pair(defaultParameters, FONT_SCALE_LARGE),
            Pair(defaultParameters, FONT_SCALE_LARGEST),
            Pair(trailingIconParameters, FONT_SCALE_SMALL),
            Pair(trailingIconParameters, FONT_SCALE_DEFAULT),
            Pair(trailingIconParameters, FONT_SCALE_LARGE),
            Pair(trailingIconParameters, FONT_SCALE_LARGEST),
            Pair(leadingIconParameters, FONT_SCALE_SMALL),
            Pair(leadingIconParameters, FONT_SCALE_DEFAULT),
            Pair(leadingIconParameters, FONT_SCALE_LARGE),
            Pair(leadingIconParameters, FONT_SCALE_LARGEST),
        )

        private val defaultParameters =
            ButtonParametersV2(
                text = "Secondary outlined",
                buttonType = ButtonTypePreview.SecondaryOutlined,
            )

        private val trailingIconParameters =
            ButtonParametersV2(
                text = "Secondary outlined (trailing)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Trailing,
            )

        private val leadingIconParameters =
            ButtonParametersV2(
                text = "Secondary outlined (leading)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Leading,
            )
    }
}
