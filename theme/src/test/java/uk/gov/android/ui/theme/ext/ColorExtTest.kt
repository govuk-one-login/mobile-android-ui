package uk.gov.android.ui.theme.ext

import androidx.compose.ui.graphics.Color
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ColorExtTest {

    @Test
    fun blackIsDark() {
        assertTrue(Color.Black.isDark())
    }

    @Test
    fun blackIsNotLight() {
        assertFalse(Color.Black.isLight())
    }

    @Test
    fun whiteIsLight() {
        assertTrue(Color.White.isLight())
    }

    @Test
    fun whiteIsNotDark() {
        assertFalse(Color.White.isDark())
    }

    @Test
    fun colorToHexString() {
        assertEquals("#FFFFFF", Color.White.toHexString())
        assertEquals("#000000", Color.Black.toHexString())
    }

    @Test
    fun colorToHexStringWithAlpha() {
        assertEquals("#FFFFFFFF", Color.White.toHexStringWithAlpha())
        assertEquals("#FF000000", Color.Black.toHexStringWithAlpha())
    }

    @Test
    fun fallbackTo() {
        assertEquals(Color.White, Color.Unspecified.fallbackTo(Color.White))
        assertEquals(Color.Black, Color.Unspecified.fallbackTo(Color.Black))
        assertEquals(Color.Black, Color.Black.fallbackTo(Color.White))
    }
}
