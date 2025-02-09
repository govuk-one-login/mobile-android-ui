package uk.gov.android.ui.patterns.centrealignedscreen

import android.content.Context
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GdsCentreAlignedScreenTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val expectedParameterSize = 8
    private val contentList = ContentProvider().values.toList()
    private val sampleContent =
        ContentProvider().values.first() // this content has all of the possible elements set

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            contentList.size,
        )
    }

    @Test
    fun testAllElementsAreDisplayed() {
        composeTestRule.setContent {
            GdsCentreAlignedScreen(sampleContent)
        }

        composeTestRule.onNodeWithText(context.getString(sampleContent.title))
            .assertExists()

        sampleContent.image?.description?.let {
            composeTestRule.onNode(hasContentDescription(context.getString(it)))
                .assertExists()
        }

        sampleContent.body?.bodyContentList?.forEach {
            when (it) {
                is BodyContent.Text ->
                    composeTestRule.onAllNodes(hasText(context.getString(it.bodyText)))
                        .assertCountEquals(2)

                is BodyContent.BulletList -> {
                    val bulletListTitle = it.bulletList.title
                    bulletListTitle?.let {
                        composeTestRule.onNodeWithText(context.getString(bulletListTitle))
                            .assertExists()
                    }
                }
            }
        }

        sampleContent.supportingText?.let {
            composeTestRule.onNodeWithText(context.getString(it)).assertExists()
        }

        sampleContent.primaryButtonText?.let {
            composeTestRule.onNodeWithText(context.getString(it)).assertExists()
        }

        sampleContent.secondaryButtonText?.let {
            composeTestRule.onNodeWithText(context.getString(it)).assertExists()
        }
    }

    @Test
    fun testNoImageDisplayedWhenNotProvided() {
        val contentWithoutImage = sampleContent.copy(image = null)
        composeTestRule.setContent {
            GdsCentreAlignedScreen(contentWithoutImage)
        }

        assertNotNull("Image must not be null for this test", sampleContent.image)

        sampleContent.image?.description?.let {
            composeTestRule.onNode(
                hasContentDescription(
                    context.getString(it),
                ),
            ).assertDoesNotExist()
        }
    }

    @Test
    fun testPrimaryButtonDoesNotExistIfNotProvided() {
        val contentWithoutPrimaryButton = sampleContent.copy(primaryButtonText = null)
        composeTestRule.setContent {
            GdsCentreAlignedScreen(contentWithoutPrimaryButton)
        }

        sampleContent.primaryButtonText?.let {
            composeTestRule.onNodeWithText(context.getString(it))
                .assertDoesNotExist()
        }
    }
}
