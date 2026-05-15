package uk.gov.android.ui.patterns.errorscreen.v2

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.heading.GdsHeading
import uk.gov.android.ui.componentsv2.images.GdsIcon
import uk.gov.android.ui.patterns.errorscreen.v2.ErrorScreenTitleTestTag.ERROR_BODY_LAZY_COLUMN_TEST_TAG
import uk.gov.android.ui.patterns.testutils.Matchers.assertListSemanticsCleared
import uk.gov.android.ui.patterns.utils.matchers.ScrollableWithKeyboardMatchers.hasKeyboardScroll

@RunWith(RobolectricTestRunner::class)
class ErrorScreenSemanticsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `lazy column has keyboard scroll enabled`() {
        composeTestRule.setErrorScreenContent()

        composeTestRule
            .onNodeWithTag(ERROR_BODY_LAZY_COLUMN_TEST_TAG)
            .assert(hasKeyboardScroll())
    }

    @Test
    fun `lazy column has semantic collection info with rows and columns set to zero`() {
        composeTestRule.setErrorScreenContent()

        composeTestRule
            .onNodeWithTag(ErrorScreenTitleTestTag.ERROR_BODY_LAZY_COLUMN_TEST_TAG)
            .assertListSemanticsCleared()
    }

    private fun ComposeContentTestRule.setErrorScreenContent() = this.setContent {
        ErrorScreen(
            icon = {
                GdsIcon(
                    image = ImageVector.vectorResource(ErrorScreenIcon.ErrorIcon.icon),
                    contentDescription = "Error",
                )
            },
            title = { GdsHeading("Title") },
        )
    }
}
