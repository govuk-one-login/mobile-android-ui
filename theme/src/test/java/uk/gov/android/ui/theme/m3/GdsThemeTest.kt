package uk.gov.android.ui.theme.m3

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GdsThemeTest {

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
}
