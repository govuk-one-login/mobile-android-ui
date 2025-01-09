package uk.gov.android.ui.componentsV2

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
import uk.gov.android.ui.theme.m3.GdsTheme

class GdsContentTileTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = ContentTilePreviewParameters().values.toList()
    private var onClick: Int = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        onClick = 0
    }

    @Test
    fun checkTileWithPrimaryBtnAndAllComponents() {
        setupContent(parameters[0])
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.vector_image_content_description),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.body),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkTileWithSecondaryBtnAndTitle() {
        setupContent(parameters[1])
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.vector_image_content_description),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.secondary_button),
                substring = true,
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkTileWithPrimaryBtnAndNoImage() {
        setupContent(parameters[2])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.body),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkTileWithPrimaryBtnAndNoImageAndNoBody() {
        setupContent(parameters[3])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkTileWithTitleAndCaption() {
        setupContent(parameters[4])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            assertEquals(0, onClick)
        }
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            GdsTheme {
                ContentTilePreview(parameters[0])
            }
        }
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.vector_image_content_description),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.body),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).assertIsDisplayed()
        }
    }

    private fun setupContent(parameters: ContentTileParameters) {
        composeTestRule.setContent {
            GdsTheme {
                GdsContentTile(parameters) {
                    onClick++
                }
            }
        }
    }
}
