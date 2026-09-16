package uk.gov.android.ui.theme.m3

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GdsThemeV2Test {

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
                Text("V2 Content test")
            }
        }
    }

    @Test
    fun `test dark theme`() {
        composeTestRule.setContent {
            GdsTheme(colorScheme = DarkColorPaletteV2) {
                Text("V2 darkTheme true test")
            }
        }
    }

    @Test
    fun `test light theme`() {
        composeTestRule.setContent {
            GdsTheme(colorScheme = LightColorPaletteV2) {
                Text("V2 darkTheme false test")
            }
        }
    }

    @Test
    fun `colour scheme can be overridden`() {
        val customColorHex = "FF123456"
        val customColorScheme = lightColorScheme(
            primary = Color.fromHex(customColorHex),
        )

        composeTestRule.setContent {
            GdsTheme(colorScheme = customColorScheme) {
                // Display the colour code as text
                Text(MaterialTheme.colorScheme.primary.toHex())
            }
        }

        composeTestRule
            .onNodeWithText(customColorHex)
            .assertExists()
    }

    @Test
    fun `extended colour scheme can be overridden`() {
        val customColorHex = "FF654321"

        composeTestRule.setContent {
            val customExtendedColorScheme = CustomColorsScheme(
                spinnerIcon = Color.fromHex(customColorHex),
            )

            GdsTheme(extendedColorScheme = customExtendedColorScheme) {
                // Display the colour code as text
                Text(GdsLocalColorScheme.current.spinnerIcon.toHex())
            }
        }

        composeTestRule.onNodeWithText(customColorHex).assertExists()
    }

    private fun Color.toHex(): String = "%08X".format(toArgb())

    private fun Color.Companion.fromHex(hex: String): Color = Color(hex.toLong(radix = HEX_RADIX))

    private companion object {
        const val HEX_RADIX = 16
    }
}
