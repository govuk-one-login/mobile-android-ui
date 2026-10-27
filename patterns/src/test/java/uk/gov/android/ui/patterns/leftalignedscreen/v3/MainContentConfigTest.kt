package uk.gov.android.ui.patterns.leftalignedscreen.v3

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import uk.gov.android.ui.theme.spacingDouble

class MainContentConfigTest {
    @Test
    fun `default values`() {
        val config = MainContentConfig()

        assertEquals(spacingDouble, config.horizontalPadding)
        assertEquals(spacingDouble, config.verticalPadding)
        assertEquals(Modifier, config.modifier)
    }

    @Test
    fun `custom values are retained`() {
        val modifier = Modifier.padding(8.dp)
        val config = MainContentConfig(
            horizontalPadding = 24.dp,
            verticalPadding = 32.dp,
            modifier = modifier,
        )

        assertEquals(24.dp, config.horizontalPadding)
        assertEquals(32.dp, config.verticalPadding)
        assertEquals(modifier, config.modifier)
    }

    @Test
    fun `copy overrides only the given field`() {
        val original = MainContentConfig()

        val copy = original.copy(horizontalPadding = 24.dp)

        assertEquals(24.dp, copy.horizontalPadding)
        // Remaining fields are unchanged.
        assertEquals(original.verticalPadding, copy.verticalPadding)
        assertEquals(original.modifier, copy.modifier)
    }

    @Test
    fun `equality for matching values`() {
        assertEquals(MainContentConfig(), MainContentConfig())
        assertEquals(
            MainContentConfig(horizontalPadding = 10.dp, verticalPadding = 12.dp),
            MainContentConfig(horizontalPadding = 10.dp, verticalPadding = 12.dp),
        )
    }

    @Test
    fun `inequality for differing values`() {
        assertNotEquals(
            MainContentConfig(horizontalPadding = 8.dp),
            MainContentConfig(horizontalPadding = 16.dp),
        )
    }
}
