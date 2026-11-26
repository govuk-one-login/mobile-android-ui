package uk.gov.android.ui.componentsv2.card.v2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.theme.m3.GdsLocalColorScheme
import uk.gov.android.ui.theme.m3.GdsTheme

@RunWith(RobolectricTestRunner::class)
class GdsCardDefaultsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `shape is a rounded corner shape with a 16dp radius`() {
        assertEquals(RoundedCornerShape(16.dp), GdsCardDefaults.shape)
    }

    @Test
    fun `colors use the card background and on background colours by default`() {
        lateinit var actual: CardColors
        lateinit var expected: CardColors
        composeTestRule.setContent {
            GdsTheme {
                actual = GdsCardDefaults.colors()
                expected = expectedColors(
                    containerColor = GdsLocalColorScheme.current.cardBackground,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        composeTestRule.waitForIdle()
        assertEquals(expected, actual)
    }

    @Test
    fun `colors can be overridden`() {
        lateinit var actual: CardColors
        lateinit var expected: CardColors
        composeTestRule.setContent {
            GdsTheme {
                expected = expectedColors(Color.Red, Color.Blue)
                actual = GdsCardDefaults.colors(
                    containerColor = Color.Red,
                    contentColor = Color.Blue,
                )
            }
        }

        composeTestRule.waitForIdle()
        assertEquals(expected, actual)
    }

    @Test
    fun `disabled colors match enabled colors`() {
        lateinit var actual: CardColors
        lateinit var withDifferentDisabled: CardColors
        composeTestRule.setContent {
            GdsTheme {
                actual = GdsCardDefaults.colors(
                    containerColor = Color.Red,
                    contentColor = Color.Blue,
                )
                withDifferentDisabled = CardDefaults.cardColors(
                    containerColor = Color.Red,
                    contentColor = Color.Blue,
                )
            }
        }

        composeTestRule.waitForIdle()
        // Equality compares the disabled colours too, so matching ones must differ from M3 defaults
        assertNotEquals(withDifferentDisabled, actual)
    }

    @Test
    fun `elevation is the Material filled card elevation`() {
        lateinit var actual: CardElevation
        lateinit var expected: CardElevation
        composeTestRule.setContent {
            actual = GdsCardDefaults.elevation()
            expected = CardDefaults.cardElevation()
        }

        composeTestRule.waitForIdle()
        assertEquals(expected, actual)
    }

    @Test
    fun `elevatedElevation is the Material elevated card elevation`() {
        lateinit var actual: CardElevation
        lateinit var expected: CardElevation
        composeTestRule.setContent {
            actual = GdsCardDefaults.elevatedElevation()
            expected = CardDefaults.elevatedCardElevation()
        }

        composeTestRule.waitForIdle()
        assertEquals(expected, actual)
    }

    @Test
    fun `border defaults to 1dp of the card border colour`() {
        lateinit var actual: BorderStroke
        var expectedColor = Color.Unspecified
        composeTestRule.setContent {
            GdsTheme {
                actual = GdsCardDefaults.border()
                expectedColor = GdsLocalColorScheme.current.cardBorder
            }
        }

        composeTestRule.waitForIdle()
        assertEquals(1.dp, actual.width)
        assertEquals(expectedColor, (actual.brush as SolidColor).value)
    }

    @Test
    fun `border can be overridden`() {
        lateinit var actual: BorderStroke
        composeTestRule.setContent {
            GdsTheme {
                actual = GdsCardDefaults.border(width = 2.dp, color = Color.Green)
            }
        }

        composeTestRule.waitForIdle()
        assertEquals(BorderStroke(2.dp, Color.Green), actual)
    }

    @Composable
    private fun expectedColors(containerColor: Color, contentColor: Color): CardColors =
        CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor,
        )
}
