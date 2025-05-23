package uk.gov.android.ui.componentsv2

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class GdsCardScreenshotTest(
    private val parameters: Pair<GdsCardPreviewParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsCard(
            title = stringResource(parameters.title),
            onClick = {},
            image = parameters.image?.let { painterResource(it) },
            contentDescription = parameters.contentDescription?.let { stringResource(it) },
            showDismissIcon = parameters.showDismissIcon,
            caption = parameters.caption?.let { stringResource(it) },
            body = parameters.body?.let { stringResource(it) },
            displayPrimary = parameters.displayPrimary,
            buttonText = parameters.buttonText?.let { stringResource(it) },
            displaySecondary = parameters.displaySecondary,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsCardV2")
        fun values(): List<Pair<GdsCardPreviewParameters, NightMode>> {
            val result: MutableList<Pair<GdsCardPreviewParameters, NightMode>> = mutableListOf()

            GdsCardPreviewParametersProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
