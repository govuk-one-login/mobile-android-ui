package uk.gov.ui.components

import android.content.Context
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.ui.theme.GdsTheme
import uk.gov.ui.theme.minimumTouchTarget

@RunWith(AndroidJUnit4::class)
class ButtonsTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val expectedParameterSize = 4
    private val parameterList = ButtonProvider().values.toList()

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
    fun verifyFirstButton() = buttonTests(parameterList[0])

    @Test
    fun verifySecondButton() = buttonTests(parameterList[1])

    @Test
    fun verifyThirdButton() = buttonTests(parameterList[2])

    @Test
    fun verifyFourthButton() = buttonTests(parameterList[3])

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

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: {0}")
        fun values() = ButtonProvider().values.toList()
    }
}
