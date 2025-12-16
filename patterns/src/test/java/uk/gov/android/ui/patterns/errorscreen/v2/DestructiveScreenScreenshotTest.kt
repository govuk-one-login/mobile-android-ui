package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.heading.GdsHeadingAlignment
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.BaseScreenshotTest
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(Parameterized::class)
internal class DestructiveScreenScreenshotTest(
    private val parameters: Pair<ErrorScreenContent, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsTheme {
            ErrorScreen(
                icon = { horizontalPadding ->
                    GdsIcon(
                        image = ImageVector.vectorResource(parameters.icon.icon),
                        contentDescription = stringResource(parameters.icon.description),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = horizontalPadding),
                        color = colorScheme.onBackground,
                    )
                },
                title = { horizontalPadding ->
                    GdsHeading(
                        text = parameters.title,
                        modifier = Modifier
                            .padding(horizontal = horizontalPadding),
                        textAlign = GdsHeadingAlignment.CenterAligned,
                    )
                },
                body = { horizontalPadding ->
                    toBodyContent(parameters.body, horizontalPadding)
                },
                primaryButton = {
                    parameters.primaryButton?.let {
                        PrimaryButton(it)
                    }
                },
                secondaryButton = {
                    parameters.secondaryButton?.let {
                        SecondaryButton(it)
                    }
                },
                tertiaryButton = {
                    parameters.tertiaryButton?.let {
                        SecondaryButton(it)
                    }
                },
            )
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index} GdsContent")
        fun values(): List<Pair<ErrorScreenContent, NightMode>> {
            val result: MutableList<Pair<ErrorScreenContent, NightMode>> = mutableListOf()

            ErrorScreenContentProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
