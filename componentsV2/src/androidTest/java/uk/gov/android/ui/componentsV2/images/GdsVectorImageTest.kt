package uk.gov.android.ui.componentsV2.images

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsV2.R

class GdsVectorImageTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = VectorImageProvider().values.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testImage() {
        composeTestRule.setContent {
            GdsVectorImage(parameters[0])
        }
        composeTestRule.onNodeWithContentDescription(
            resources.getString(R.string.vector_image_content_description),
        ).assertIsDisplayed()
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            VectorImagePreview(parameters[0])
        }
        composeTestRule.onNodeWithContentDescription(
            resources.getString(R.string.vector_image_content_description),
        ).assertIsDisplayed()
    }
}
