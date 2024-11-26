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
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonProvider
import uk.gov.android.ui.components.m3.buttons.GdsButton
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.minimumTouchTarget

class ButtonsM3Test() {
    private val expectedParameterSize = 6
    private val parametersList = ButtonProvider().values.map { it.first() }.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            parametersList.size
        )
    }

    @Test
    fun buttonTest1() = buttonTest(parametersList[0])

    @Test
    fun buttonTest2() = buttonTest(parametersList[1])

    @Test
    fun buttonTest3() = buttonTest(parametersList[2])

    @Test
    fun buttonTest4() = buttonTest(parametersList[3])

    @Test
    fun buttonTest5() = buttonTest(parametersList[4])

    @Test
    fun buttonTest6() = buttonTest(parametersList[5])

    private fun buttonTest(parameters: ButtonParameters) {
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
}
