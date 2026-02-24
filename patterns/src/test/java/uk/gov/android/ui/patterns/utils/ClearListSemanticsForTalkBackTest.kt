package uk.gov.android.ui.patterns.utils

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.patterns.testutils.Matchers.assertListSemanticsCleared

@RunWith(RobolectricTestRunner::class)
class ClearListSemanticsForTalkBackTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `util applied on modifier has semantic collection info with rows and columns set to zero`() {
        composeTestRule.setContent {
            LazyColumn(
                content = { },
                modifier = Modifier
                    .clearListSemanticsForTalkBack()
                    .testTag(TEST_TAG),
            )
        }

        composeTestRule
            .onNodeWithTag(TEST_TAG)
            .assertListSemanticsCleared()
    }

    companion object {
        private const val TEST_TAG = "test tag"
    }
}
