package uk.gov.android.ui.componentsv2.row

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.componentsv2.images.Image
import uk.gov.android.ui.componentsv2.rules.ComposeContentTestRuleExtensions.onNodeWithRole

class RowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun assertRowWithImageHasCorrectContentDescription() {
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
    fun assertOpensInNewTrailingIconHasContentDescription() {
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
    fun assertNavigateNextComponent() {
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
    fun assertRowWithEnabledButtonIsClickable() {
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
    fun assertRowWithDisabledButtonIsNotClickable() {
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
}
