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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.components.buttons.ButtonParameters
import uk.gov.android.ui.components.buttons.ButtonProvider
import uk.gov.android.ui.components.buttons.GdsButton
import uk.gov.android.ui.theme.GdsTheme
import uk.gov.android.ui.theme.minimumTouchTarget

@RunWith(Parameterized::class)
class ButtonsTest(
    private val buttonParameters: ButtonParameters,
) {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val expectedParameterSize = 11

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            values().size,
        )
    }

    @Test
    fun buttonTests() {
        composeTestRule.setContent {
            GdsTheme {
                GdsButton(
                    buttonParameters,
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
