package uk.gov.ui.components

import android.content.Context
import android.content.res.Resources
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.ui.components.images.icon.GdsIconPreview
import uk.gov.ui.components.images.icon.GdsIconProvider
import uk.gov.ui.components.images.icon.IconParameters
import uk.gov.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class GdsIconComposableTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources

    private val expectedParameterListSize = 3
    private val parameterList = GdsIconProvider().values.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected parameter list size does not match the InformationProvider size!",
            expectedParameterListSize,
            parameterList.size
        )
    }

    @Test
    fun verifyFirstParameters() = verifyComposable(parameterList[0])

    @Test
    fun verifySecondParameters() = verifyComposable(parameterList[1])

    @Test
    fun verifyThirdParameters() = verifyComposable(parameterList[2])

    private fun verifyComposable(parameters: IconParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsIconPreview(parameters)
                }
            }

            parameters.verifyComposable(this, resources)
        }
    }
}

fun IconParameters.verifyComposable(
    composeTestRule: ComposeContentTestRule,
    resources: Resources
) = composeTestRule.let { rule ->
    val iconNode = rule.onNode(hasTestTag(image.toString()), true)
        .assertIsDisplayed()

    if (description != null) {
        iconNode.assertContentDescriptionEquals(
            resources.getString(description!!)
        )
    }

    if (hasSpecifiedSize()) {
        iconNode
            .assertHeightIsEqualTo(sizeAsDp()!!)
            .assertWidthIsEqualTo(sizeAsDp()!!)
    }
}
