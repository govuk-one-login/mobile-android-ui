package uk.gov.android.ui.theme

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.theme.m3.GdsThemeV2
import uk.gov.android.ui.theme.util.UnstableDesignSystemAPI

@RunWith(AndroidJUnit4::class)
class GdsThemeV2Test {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun `default local text style is body large`() {
        composeTestRule.setContent {
            GdsThemeV2 {
                val localDefault = LocalTextStyle.current
                val bodyLarge = MaterialTheme.typography.bodyLarge
                assertEquals(bodyLarge, localDefault)
            }
        }
    }

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun `test content`() {
        composeTestRule.setContent {
            GdsThemeV2 {
                Text("V2 Content test")
            }
        }
    }

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun `test darkTheme true`() {
        composeTestRule.setContent {
            GdsThemeV2(darkTheme = true) {
                Text("V2 darkTheme true test")
            }
        }
    }

    @OptIn(UnstableDesignSystemAPI::class)
    @Test
    fun `test darkTheme false`() {
        composeTestRule.setContent {
            GdsThemeV2(darkTheme = false) {
                Text("V2 darkTheme false test")
            }
        }
    }
}
