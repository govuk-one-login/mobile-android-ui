package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import uk.gov.android.ui.theme.spacingDouble

class BottomContentConfigTest {
    @Test
    fun `default values`() {
        val config = BottomContentConfig()

        assertEquals(Color.Unspecified, config.backgroundColor)
        assertEquals(spacingDouble, config.horizontalPadding)
        assertEquals(spacingDouble, config.verticalPadding)
        assertEquals(Modifier, config.modifier)
    }

    @Test
    fun `custom values are retained`() {
        val modifier = Modifier.padding(8.dp)
        val config = BottomContentConfig(
            backgroundColor = Color.Red,
            horizontalPadding = 24.dp,
            verticalPadding = 32.dp,
            modifier = modifier,
        )

        assertEquals(Color.Red, config.backgroundColor)
        assertEquals(24.dp, config.horizontalPadding)
        assertEquals(32.dp, config.verticalPadding)
        assertEquals(modifier, config.modifier)
    }

    @Test
    fun `copy overrides only the given field`() {
        val original = BottomContentConfig()

        val copy = original.copy(backgroundColor = Color.Blue)

        assertEquals(Color.Blue, copy.backgroundColor)
        // Remaining fields are unchanged.
        assertEquals(original.horizontalPadding, copy.horizontalPadding)
        assertEquals(original.verticalPadding, copy.verticalPadding)
        assertEquals(original.modifier, copy.modifier)
    }

    @Test
    fun `equality for matching values`() {
        assertEquals(BottomContentConfig(), BottomContentConfig())
        assertEquals(
            BottomContentConfig(backgroundColor = Color.Green, horizontalPadding = 10.dp),
            BottomContentConfig(backgroundColor = Color.Green, horizontalPadding = 10.dp),
        )
    }

    @Test
    fun `inequality for differing values`() {
        assertNotEquals(
            BottomContentConfig(backgroundColor = Color.Red),
            BottomContentConfig(backgroundColor = Color.Blue),
        )
        assertNotEquals(
            BottomContentConfig(horizontalPadding = 8.dp),
            BottomContentConfig(horizontalPadding = 16.dp),
        )
        assertNotEquals(
            BottomContentConfig(verticalPadding = 8.dp),
            BottomContentConfig(verticalPadding = 16.dp),
        )
    }
}
