package uk.gov.android.ui.pages

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.theme.m3.GdsTheme

class LoadingScreenTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val defaultParameters = LoadingScreenParameterProvider().values.toList()[0]
    private val defaultParametersNoNavIcon = LoadingScreenParameterProvider().values.toList()[2]
    private val customParameters = LoadingScreenParameterProvider().values.toList()[1]
    private val customParametersNoNavIcon = LoadingScreenParameterProvider().values.toList()[3]
    private var onClickNavIcon = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultDisplay() {
        setupDefault(display = true)
        composeTestRule.apply {
            assertEquals(0, onClickNavIcon)

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__default__text),
            ).assertIsDisplayed()

            assertEquals(1, onClickNavIcon)
        }
    }

    @Test
    fun defaultDisplayNoNavIcon() {
        setupDefault(display = false)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).apply {
                assertDoesNotExist()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__default__text),
            ).assertIsDisplayed()
        }
    }

    @Test
    fun customDisplay() {
        setup(display = true)
        composeTestRule.apply {
            assertEquals(0, onClickNavIcon)

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__text),
            ).assertIsDisplayed()

            assertEquals(1, onClickNavIcon)
        }
    }

    @Test
    fun customDisplayNoNavIcon() {
        setup(display = false)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).apply {
                assertDoesNotExist()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__text),
            ).assertIsDisplayed()
        }
    }

    @Test
    fun defaultPreviewDisplay() {
        setupPreview(defaultParameters)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__default__text),
            ).assertIsDisplayed()
        }
    }

    @Test
    fun defaultPreviewDisplayNoNavIcon() {
        setupPreview(defaultParametersNoNavIcon)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).apply {
                assertDoesNotExist()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__default__text),
            ).assertIsDisplayed()
        }
    }

    @Test
    fun customPreviewDisplay() {
        setupPreview(customParameters)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__text),
            ).assertIsDisplayed()
        }
    }

    @Test
    fun customPreviewDisplayNoNavIcon() {
        setupPreview(customParametersNoNavIcon)
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__navIcon__contentDescription),
            ).apply {
                assertDoesNotExist()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.loadingScreen__progressBar__contentDescription),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.loadingScreen__text),
            ).assertIsDisplayed()
        }
    }

    private fun setupDefault(display: Boolean) {
        composeTestRule.setContent {
            onClickNavIcon = 0
            GdsTheme {
                LoadingScreen(
                    parameters = LoadingScreenParameters(
                        displayNavIcon = display,
                        onClickNavIcon = { onClickNavIcon++ },
                    ),
                )
            }
        }
    }

    private fun setup(display: Boolean) {
        composeTestRule.setContent {
            onClickNavIcon = 0
            GdsTheme {
                LoadingScreen(
                    parameters = LoadingScreenParameters(
                        displayText = customParameters.displayText,
                        displayNavIcon = display,
                        onClickNavIcon = { onClickNavIcon++ },
                    ),
                )
            }
        }
    }

    private fun setupPreview(parameters: LoadingScreenParameters) {
        composeTestRule.setContent {
            GdsTheme {
                LoadingScreenPreview(parameters = parameters)
            }
        }
    }
}
