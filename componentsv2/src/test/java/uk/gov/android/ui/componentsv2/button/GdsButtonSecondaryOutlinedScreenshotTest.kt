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
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.theme.xsmallPadding

@RunWith(Parameterized::class)
internal class GdsButtonSecondaryOutlinedScreenshotTest(
    private val parameters: Pair<ButtonParametersV2, NightMode>,
) : BaseScreenshotTest(parameters.second) {

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
                    modifier = parameters.modifier,
                    text = parameters.text,
                    buttonType = parameters.buttonType.toButtonTypeV2(),
                    icon = parameters.icon?.toButtonIcon(),
                    onClick = {},
                    contentPosition = parameters.contentPosition,
                    contentModifier = parameters.contentModifier,
                    textAlign = parameters.textAlign,
                    enabled = parameters.enabled,
                    loading = parameters.loading,
                )
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsButtonSecondaryOutlined")
        fun values(): List<Pair<ButtonParametersV2, NightMode>> {
            val result: MutableList<Pair<ButtonParametersV2, NightMode>> = mutableListOf()

            secondaryOutlinedParameters.forEach(applyNightMode(result))

            return result
        }

        private val secondaryOutlinedParameters = listOf(
            ButtonParametersV2(
                text = "Secondary outlined button",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                modifier = Modifier.fillMaxWidth(),
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (icon)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Trailing,
                modifier = Modifier.fillMaxWidth(),
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (leading icon)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Leading,
                modifier = Modifier.fillMaxWidth(),
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (disabled)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (disabled, icon)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                icon = ButtonIconPreview.Trailing,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
            ),
            ButtonParametersV2(
                text = "Secondary outlined button (loading)",
                buttonType = ButtonTypePreview.SecondaryOutlined,
                loading = true,
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
            ),
        )
    }
}
