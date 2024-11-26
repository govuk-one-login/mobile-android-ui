package uk.gov.android.ui.components

import android.content.Context
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonProvider
import uk.gov.android.ui.components.buttons.GdsButton
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.minimumTouchTarget

class ButtonsTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val parameterList = ButtonProvider().values.toList()
    private val expectedParameterSize = 11

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            parameterList.size
        )
    }

    @Test
    fun buttonTest1() = buttonTests(parameterList[0])

    @Test
    fun buttonTest2() = buttonTests(parameterList[1])

    @Test
    fun buttonTest3() = buttonTests(parameterList[2])

    @Test
    fun buttonTest4() = buttonTests(parameterList[3])

    @Test
    fun buttonTest5() = buttonTests(parameterList[4])

    @Test
    fun buttonTest6() = buttonTests(parameterList[5])

    @Test
    fun buttonTest7() = buttonTests(parameterList[6])

    @Test
    fun buttonTest8() = buttonTests(parameterList[7])

    @Test
    fun buttonTest9() = buttonTests(parameterList[8])

    @Test
    fun buttonTest10() = buttonTests(parameterList[9])

    @Test
    fun buttonTest11() = buttonTests(parameterList[10])

    private fun buttonTests(buttonParameters: ButtonParameters) {
        composeTestRule.setContent {
            GdsTheme {
                GdsButton(
                    buttonParameters
                )
            }
        }

        composeTestRule.apply {
            onNodeWithText(resources.getString(buttonParameters.text)).apply {
                assertIsDisplayed()
                assertHeightIsAtLeast(minimumTouchTarget)
                assertWidthIsAtLeast(minimumTouchTarget)
            }
        }
    }
}
