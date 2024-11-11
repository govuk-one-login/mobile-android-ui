package uk.gov.android.ui.components.m3

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.components.R
import uk.gov.android.ui.theme.m3.GdsTheme

class GdsInformationBannerTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = InformationBannerParamProvider().values.toList()[0]
    private var onDismissClick = 0
    private var onCardClick = 0
    private var onLinkClick = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayText() {
        setup()
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.preview__GdsInformationBanner__contentDescription),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(
                    R.string.preview__GdsInformationBanner__dismissIcon__contentDescription
                ),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__title),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__content),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__link),
                substring = true,
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(
                    R.string.preview__GdsInformationBanner__linkIcon__contentDescription
                ),
                useUnmergedTree = true
            ).assertIsDisplayed()
        }
    }

    @Test
    fun checkOnClick() {
        setupSimpleLink()
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.preview__GdsInformationBanner__contentDescription),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(
                    R.string.preview__GdsInformationBanner__dismissIcon__contentDescription
                ),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__title),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__content),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__link),
                substring = true,
                useUnmergedTree = true
            ).assertIsDisplayed()
        }
    }

    @Test
    fun displayTextSimpleLink() {
        setup()
        composeTestRule.apply {
            assertEquals(0, onCardClick)
            assertEquals(0, onLinkClick)
            assertEquals(0, onDismissClick)

            onNodeWithContentDescription(
                resources.getString(R.string.preview__GdsInformationBanner__contentDescription),
                useUnmergedTree = true
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(1, onCardClick)
            assertEquals(1, onLinkClick)
            assertEquals(0, onDismissClick)

            onNodeWithContentDescription(
                resources.getString(
                    R.string.preview__GdsInformationBanner__dismissIcon__contentDescription
                ),
                useUnmergedTree = true
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(1, onCardClick)
            assertEquals(1, onLinkClick)
            assertEquals(1, onDismissClick)

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__link),
                substring = true,
                useUnmergedTree = true
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(2, onCardClick)
            assertEquals(2, onLinkClick)
            assertEquals(1, onDismissClick)
        }
    }

    @Test
    fun displayTextPreview() {
        setupPreview()
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.preview__GdsInformationBanner__contentDescription),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(
                    R.string.preview__GdsInformationBanner__dismissIcon__contentDescription
                ),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__title),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__content),
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.preview__GdsInformationBanner__link),
                substring = true,
                useUnmergedTree = true
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(
                    R.string.preview__GdsInformationBanner__linkIcon__contentDescription
                ),
                useUnmergedTree = true
            ).assertIsDisplayed()
        }
    }

    private fun setup() {
        composeTestRule.setContent {
            onCardClick = 0
            onLinkClick = 0
            onDismissClick = 0
            GdsTheme {
                GdsInformationBanner(
                    parameters = InformationBannerParameters(
                        title = R.string.preview__GdsInformationBanner__title,
                        content = R.string.preview__GdsInformationBanner__content,
                        link = R.string.preview__GdsInformationBanner__link,
                        icon = Icons.Default.ArrowForward,
                        onClick = {
                            onCardClick++
                            onLinkClick++
                        },
                        onDismiss = { onDismissClick++ }
                    )
                )
            }
        }
    }

    private fun setupSimpleLink() {
        composeTestRule.setContent {
            GdsTheme {
                GdsInformationBanner(
                    parameters = InformationBannerParameters(
                        title = R.string.preview__GdsInformationBanner__title,
                        content = R.string.preview__GdsInformationBanner__content,
                        link = R.string.preview__GdsInformationBanner__link,
                        onClick = {
                            onCardClick++
                            onLinkClick++
                        },
                        onDismiss = { onDismissClick++ }
                    )
                )
            }
        }
    }

    private fun setupPreview() {
        composeTestRule.setContent {
            InformationBannerPreview(parameters = parameters)
        }
    }
}
