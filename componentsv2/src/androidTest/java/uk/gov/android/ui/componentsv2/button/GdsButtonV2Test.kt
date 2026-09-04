package uk.gov.android.ui.componentsv2.button

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.componentsv2.button.previewparameterprovider.ButtonParameterPreviewProviderV2

class GdsButtonV2Test {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = ButtonParameterPreviewProviderV2().values.toList()
    private var onClick = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        onClick = 0
    }

    @Test
    fun testPrimary() {
        setupContent(parameters[0])
        composeTestRule.onNodeWithText(
            parameters[0].text
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testSecondary() {
        setupContent(parameters[1])
        composeTestRule.onNodeWithText(
            parameters[1].text
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testTertiary() {
        setupContent(parameters[2])
        composeTestRule.onNodeWithText(
            parameters[2].text
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testQuaternary() {
        setupContent(parameters[3])
        composeTestRule.onNodeWithText(
            parameters[3].text
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testAdmin() {
        setupContent(parameters[4])
        composeTestRule.onNodeWithText(
            parameters[4].text
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testError() {
        setupContent(parameters[5])
        composeTestRule.onNodeWithText(
            parameters[5].text
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testIcon() {
        setupContent(parameters[6])
        composeTestRule.apply {
            onNodeWithText(
                parameters[6].text,
                substring = true
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.opens_in_external_browser)
            ).assertIsDisplayed()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testIconSecondary() {
        setupContent(parameters[11])
        composeTestRule.apply {
            onNodeWithText(
                parameters[11].text,
                substring = true
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.opens_in_external_browser)
            ).assertIsDisplayed()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testCustom() {
        setupContent(parameters[12])
        composeTestRule.apply {
            onNodeWithText(
                parameters[12].text,
                substring = true
            ).apply {
                assertIsDisplayed()
                performClick()
            }
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testLoading() {
        setupContent(parameters[9])
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.loading_content_desc)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun testErrorSecondary() {
        setupContent(parameters[14])
        composeTestRule.onNodeWithText(
            parameters[14].text
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            ButtonPreviewV2(parameters[0])
        }
        composeTestRule.onNodeWithText(
            parameters[0].text
        ).assertIsDisplayed()
    }

    private fun setupContent(parameters: ButtonParametersV2) {
        composeTestRule.setContent {
            GdsButton(
                text = parameters.text,
                icon = parameters.icon?.toButtonIcon(),
                buttonType = parameters.buttonType.toButtonTypeV2(),
                onClick = { onClick++ },
                loading = parameters.loading
            )
        }
    }
}
