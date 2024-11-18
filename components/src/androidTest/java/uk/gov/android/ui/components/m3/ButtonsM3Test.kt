package uk.gov.android.ui.components.m3

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonProvider
import uk.gov.android.ui.components.m3.buttons.GdsButton
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.minimumTouchTarget

@RunWith(Parameterized::class)
class ButtonsM3Test(
    private val parameters: ButtonParameters
) {
    private val expectedParameterSize = 6

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            values().size
        )
    }

    @Test
    fun buttonTests() {
        composeTestRule.setContent {
            GdsTheme {
                GdsButton(
                    parameters
                )
            }
        }

        with(composeTestRule) {
            onNodeWithText(parameters.text, substring = true).apply {
                assertIsDisplayed()
                assertIsEnabled()
                assertHeightIsAtLeast(minimumTouchTarget)
                assertWidthIsAtLeast(minimumTouchTarget)
                assertHasClickAction()
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = ButtonProvider().values.map { it.first() }.toList()
    }
}
