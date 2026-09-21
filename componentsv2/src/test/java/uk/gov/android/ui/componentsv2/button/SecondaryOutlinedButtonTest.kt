package uk.gov.android.ui.componentsv2.button

import android.content.Context
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R

@RunWith(RobolectricTestRunner::class)
class SecondaryOutlinedButtonTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private var onClick = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        onClick = 0
    }

    @Test
    fun testButtonDisplaysText() {
        composeTestRule.setContent {
            SecondaryOutlinedButton(
                text = R.string.secondary_button,
                onClick = { onClick++ },
            )
        }
        composeTestRule.apply {
            onNodeWithText(
                context.getString(R.string.secondary_button),
            ).assertIsDisplayed()
        }
    }

    @Test
    fun testButtonClickInvokesCallback() {
        composeTestRule.setContent {
            SecondaryOutlinedButton(
                text = R.string.secondary_button,
                onClick = { onClick++ },
            )
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithText(
                context.getString(R.string.secondary_button),
            ).performClick()
        }
        assertEquals(1, onClick)
    }

    @Test
    fun testButtonWithIcon() {
        composeTestRule.setContent {
            SecondaryOutlinedButton(
                text = R.string.secondary_button,
                onClick = { onClick++ },
                icon = ImageVector.vectorResource(R.drawable.ic_external_site),
                iconContentDescription = context.getString(R.string.opens_in_external_browser),
            )
        }
        composeTestRule.apply {
            onNodeWithText(
                context.getString(R.string.secondary_button),
                substring = true,
            ).assertIsDisplayed()
        }
    }

    @Test
    fun testDisabledButtonDoesNotInvokeCallback() {
        composeTestRule.setContent {
            SecondaryOutlinedButton(
                text = R.string.secondary_button,
                onClick = { onClick++ },
                enabled = false,
            )
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithText(
                context.getString(R.string.secondary_button),
            ).assertIsNotEnabled()
        }
    }

    @Test
    fun testEnabledButton() {
        composeTestRule.setContent {
            SecondaryOutlinedButton(
                text = R.string.secondary_button,
                onClick = { onClick++ },
                enabled = true,
            )
        }
        composeTestRule.apply {
            onNodeWithText(
                context.getString(R.string.secondary_button),
            ).assertIsEnabled()
        }
    }

    @Test
    fun testOpensInBrowserAppendsAccessibilityText() {
        composeTestRule.setContent {
            SecondaryOutlinedButton(
                text = R.string.secondary_button,
                onClick = { onClick++ },
                opensInBrowser = true,
            )
        }
        val expectedText = "${context.getString(R.string.secondary_button)} - " +
            context.getString(R.string.opens_in_external_browser)
        composeTestRule.apply {
            onNodeWithText(expectedText).assertIsDisplayed()
        }
    }
}
