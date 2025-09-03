package uk.gov.android.ui.componentsv2.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsButtonV2ScreenshotTest(
    private val parameters: Pair<ButtonParametersV2, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsButton(
            text = stringResource(parameters.text),
            buttonType = parameters.buttonType.toButtonTypeV2(),
            onClick = {},
            contentPosition = parameters.contentPosition,
            enabled = parameters.enabled,
            loading = parameters.loading,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsButtonV2")
        fun values(): List<Pair<ButtonParametersV2, NightMode>> {
            val result: MutableList<Pair<ButtonParametersV2, NightMode>> = mutableListOf()

            ButtonParameterPreviewProviderV2().values.forEach(applyNightMode(result))

            return result
        }
    }
}
