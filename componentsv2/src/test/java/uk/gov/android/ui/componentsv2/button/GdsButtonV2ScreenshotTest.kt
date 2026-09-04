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
import androidx.compose.ui.res.stringResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.componentsv2.button.previewparameterprovider.ButtonParameterPreviewProviderV2
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.xsmallPadding

@RunWith(Parameterized::class)
internal class GdsButtonV2ScreenshotTest(
    private val parameters: Pair<ButtonParametersV2, NightMode>
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Display buttons in a full width container with visible background to help distinguish
            // full width from non-full width variants and light mode from dark mode variants
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(xsmallPadding),
                contentAlignment = Alignment.Center
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
                    loading = parameters.loading
                )
            }
        }
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
