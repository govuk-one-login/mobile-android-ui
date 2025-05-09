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
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(AndroidJUnit4::class)
class GdsThemeE2ETest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `default local text style is body large`() {
        composeTestRule.setContent {
            GdsTheme {
                val localDefault = LocalTextStyle.current
                val bodyLarge = MaterialTheme.typography.bodyLarge
                assertEquals(bodyLarge, localDefault)
            }
        }
    }

    @Test
    fun `test content`() {
        composeTestRule.setContent {
            GdsTheme {
                Text("Content test")
            }
        }
    }

    @Test
    fun `test darkTheme true`() {
        composeTestRule.setContent {
            GdsTheme(darkTheme = true) {
                Text("darkTheme true test")
            }
        }
    }

    @Test
    fun `test darkTheme false`() {
        composeTestRule.setContent {
            GdsTheme(darkTheme = false) {
                Text("darkTheme false test")
            }
        }
    }
}
