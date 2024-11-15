package uk.gov.android.ui.pages.m3

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.pages.LandingPage
import uk.gov.android.ui.pages.LandingPageParameters
import uk.gov.android.ui.pages.LandingPageProvider
import uk.gov.android.ui.pages.R
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.smallPadding

class LandingPageTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = LandingPageProvider().values.toList()[1]
    private val parametersWithContent = LandingPageProvider().values.toList()[0]

    private var onIconClick = 0
    private var onButtonClick = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setup(parameters: LandingPageParameters) {
        composeTestRule.setContent {
            onButtonClick = 0
            onIconClick = 0
            GdsTheme {
                LandingPage(
                    landingPageParameters = parameters
                )
            }
        }
    }

    @Test
    fun testComponentsPreviewDisplayed() {
        setup(parameters)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.preview__landingPage__iconContentDescription)
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__title)
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__subtitle_1)
            ).assertDoesNotExist()

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__primary_button)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun testComponentsPreviewWithContentDisplayed() {
        setup(parametersWithContent)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.preview__landingPage__iconContentDescription)
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__title)
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__subtitle_1)
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__primary_button)
            ).assertIsDisplayed()
        }
    }

    @Test
    fun testIconClick() {
        setup(
            LandingPageParameters(
                topIcon = R.drawable.ic_photo_camera,
                iconPadding = PaddingValues(bottom = smallPadding),
                contentDescription = R.string.preview__landingPage__iconContentDescription,
                onTopIconClick = { onIconClick++ },
                title = R.string.preview__BrpInstructions__title,
                primaryButtonText = R.string.preview__BrpInstructions__primary_button
            )
        )
        composeTestRule.apply {
            assertEquals(0, onIconClick)

            onNodeWithContentDescription(
                resources.getString(R.string.preview__landingPage__iconContentDescription)
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__title)
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__primary_button)
            ).assertIsDisplayed()

            assertEquals(1, onIconClick)
        }
    }

    @Test
    fun testButtonClick() {
        setup(
            LandingPageParameters(
                topIcon = R.drawable.ic_photo_camera,
                iconPadding = PaddingValues(bottom = smallPadding),
                contentDescription = R.string.preview__landingPage__iconContentDescription,
                title = R.string.preview__BrpInstructions__title,
                primaryButtonText = R.string.preview__BrpInstructions__primary_button,
                onPrimary = { onButtonClick++ }
            )
        )
        composeTestRule.apply {
            assertEquals(0, onButtonClick)

            onNodeWithText(
                resources.getString(R.string.preview__BrpInstructions__primary_button)
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(1, onButtonClick)
        }
    }
}
