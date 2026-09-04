package uk.gov.android.ui.componentsv2.row

import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.Density
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.componentsv2.rules.ComposeContentTestRuleExtensions.onNodeWithRole

@RunWith(RobolectricTestRunner::class)
class RowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `assert row with leading image has correct content description`() {
        composeTestRule.setContent {
            Row(
                title = "Title",
                leadingImage = Image(
                    drawable = R.drawable.placeholder_leading_image,
                    contentDescription = "Test content description",
                ),
                subtitle = "Subtitle",
                onClick = {},
            )
        }
        with(composeTestRule) {
            onNodeWithContentDescription("Test content description").assertExists()
        }
    }

    @Test
    fun `assert opens_in_new trailing icon has correct content description`() {
        composeTestRule.setContent {
            Row(
                title = "Title",
                leadingImage = Image(
                    drawable = R.drawable.placeholder_leading_image,
                    contentDescription = "Test content description",
                ),
                trailingIcon = RowTrailingIcon.OpenInNew(),
                subtitle = "Subtitle",
                onClick = {},
            )
        }
        with(composeTestRule) {
            onNodeWithContentDescription("opens in web browser").assertExists()
        }
    }

    @Test
    fun `assert row with enabled click behaviour is clickable`() {
        var count = 0
        val onClick: () -> Unit = { count++ }
        composeTestRule.setContent {
            Row(
                title = "Title",
                subtitle = "Subtitle",
                clickEnabled = true,
                onClick = onClick,
            )
        }

        with(composeTestRule) {
            onNodeWithRole(Role.Button).assertExists()
            onNodeWithText("Title").performClick()
        }
        assertEquals(1, count)
    }

    @Test
    fun `assert row with disabled click behaviour is not clickable`() {
        var count = 0
        val onClick: () -> Unit = { count++ }
        composeTestRule.setContent {
            Row(
                title = "Title",
                subtitle = "Subtitle",
                clickEnabled = false,
                onClick = onClick,
            )
        }

        with(composeTestRule) {
            onNodeWithRole(Role.Button).assertDoesNotExist()
            onNodeWithText("Title").performClick()
        }
        assertEquals(0, count)
    }

    @Test
    fun `assert leading image scaling calculation is correct`() {
        val resources = mock<Resources>()
        val displayMetrics = DisplayMetrics().apply {
            xdpi = 420f
            ydpi = 420f
        }
        val density = Density(5.25f)
        whenever(resources.displayMetrics).thenReturn(displayMetrics)

        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalResources provides resources,
                LocalDensity provides density,
            ) {
                val scalingFactor = getDisplayScalingFactor()
                assertEquals(2f, scalingFactor)
            }
        }
    }

    @Test
    fun `assert leading image scaling calculation does not cause infinite size image`() {
        val resources = mock<Resources>()
        val displayMetrics = DisplayMetrics().apply {
            xdpi = 0f
            ydpi = 0f
        }
        val density = Density(5.25f)
        whenever(resources.displayMetrics).thenReturn(displayMetrics)

        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalResources provides resources,
                LocalDensity provides density,
            ) {
                val scalingFactor = getDisplayScalingFactor()
                assertEquals(1f, scalingFactor)
            }
        }
    }
}
