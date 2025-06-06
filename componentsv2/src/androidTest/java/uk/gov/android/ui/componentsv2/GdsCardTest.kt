package uk.gov.android.ui.componentsv2

import android.content.Context
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GdsCardTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = GdsCardPreviewParametersProvider().values.toList()
    private var onClick: Int = 0
    private var dismissIconButton: Boolean = false

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        onClick = 0
        dismissIconButton = false
    }

    @Test
    fun checkCardWithPrimaryBtnAndAllComponents() {
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

            onNodeWithContentDescription(
                resources.getString(R.string.close_button),
            ).assertIsDisplayed().performClick()

            assertEquals(1, onClick)
            assertTrue(dismissIconButton)
        }
    }

    @Test
    fun checkCardWithSecondaryBtnAndTitle() {
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

            // The same icon description is used for the AnnotatedString used for Secondary Button
            onAllNodesWithContentDescription(
                resources.getString(R.string.opens_in_external_browser),
            ).assertCountEquals(1)

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkCardWithPrimaryBtnAndNoImage() {
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

            onNodeWithContentDescription(
                resources.getString(R.string.close_button),
            ).assertIsNotDisplayed()

            assertEquals(1, onClick)
            assertFalse(dismissIconButton)
        }
    }

    @Test
    fun checkCardWithPrimaryBtnAndNoImageAndNoBody() {
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

            onNodeWithContentDescription(
                resources.getString(R.string.close_button),
            ).assertIsNotDisplayed()

            assertEquals(1, onClick)
            assertFalse(dismissIconButton)
        }
    }

    @Test
    fun checkCardWithTitleAndCaption() {
        setupContent(parameters[4])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(R.string.close_button),
            ).assertIsNotDisplayed()

            assertEquals(0, onClick)
            assertFalse(dismissIconButton)
        }
    }

    @Test
    fun checkCardWithPrimaryBtnAndDismissBtnButNoImage() {
        setupContent(parameters[5])
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
                resources.getString(R.string.secondary_button),
                substring = true,
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithText(
                resources.getString(R.string.opens_in_external_browser),
            ).assertDoesNotExist()

            onNodeWithContentDescription(
                resources.getString(R.string.close_button),
            ).assertIsDisplayed().performClick()

            assertEquals(1, onClick)
            assertTrue(dismissIconButton)
        }
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            GdsCardPreview(parameters[0])
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

    private fun setupContent(parameters: GdsCardPreviewParameters) {
        composeTestRule.setContent {
            GdsCard(
                title = stringResource(parameters.title),
                titleStyle = parameters.titleStyle,
                onClick = { onClick++ },
                image = parameters.image?.let { painterResource(it) },
                contentDescription = parameters.contentDescription?.let { stringResource(it) },
                showDismissIcon = parameters.showDismissIcon,
                caption = parameters.caption?.let { stringResource(it) },
                body = parameters.body?.let { stringResource(it) },
                displayPrimary = parameters.displayPrimary,
                buttonText = parameters.buttonText?.let { stringResource(it) },
                displaySecondary = parameters.displaySecondary,
                secondaryIcon = parameters.secondaryIcon?.let { ImageVector.vectorResource(it) },
                secondaryIconContentDescription = parameters.secondaryIconContentDescription?.let {
                    stringResource(it)
                },
                dismiss = { dismissIconButton = !dismissIconButton },
                shadow = parameters.shadow,
            )
        }
    }
}
