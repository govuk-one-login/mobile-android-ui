package uk.gov.android.ui.patterns.leftalignedscreen.v3

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LeftAlignedScreenContentTest {
    @Test
    fun `only title is required, other fields default to null`() {
        val content = LeftAlignedScreenContent(title = TITLE)

        assertEquals(TITLE, content.title)
        assertNull(content.body)
        assertNull(content.supportingText)
        assertNull(content.primaryButton)
        assertNull(content.secondaryButton)
    }

    @Test
    fun `custom values are retained`() {
        val body = listOf<LeftAlignedScreenBody>(LeftAlignedScreenBody.Text(BODY_TEXT))
        val content = LeftAlignedScreenContent(
            title = TITLE,
            body = body,
            supportingText = SUPPORTING_TEXT,
            primaryButton = PRIMARY_BUTTON,
            secondaryButton = SECONDARY_BUTTON,
        )

        assertEquals(TITLE, content.title)
        assertEquals(body, content.body)
        assertEquals(SUPPORTING_TEXT, content.supportingText)
        assertEquals(PRIMARY_BUTTON, content.primaryButton)
        assertEquals(SECONDARY_BUTTON, content.secondaryButton)
    }

    @Test
    fun `copy overrides only the given field`() {
        val original = LeftAlignedScreenContent(
            title = TITLE,
            supportingText = SUPPORTING_TEXT,
        )

        val copy = original.copy(primaryButton = PRIMARY_BUTTON)

        assertEquals(PRIMARY_BUTTON, copy.primaryButton)
        // Remaining fields are unchanged.
        assertEquals(original.title, copy.title)
        assertEquals(original.body, copy.body)
        assertEquals(original.supportingText, copy.supportingText)
        assertEquals(original.secondaryButton, copy.secondaryButton)
    }

    @Test
    fun `equality for matching values`() {
        assertEquals(
            LeftAlignedScreenContent(title = TITLE),
            LeftAlignedScreenContent(title = TITLE),
        )
        assertEquals(
            LeftAlignedScreenContent(
                title = TITLE,
                supportingText = SUPPORTING_TEXT,
                primaryButton = PRIMARY_BUTTON,
            ),
            LeftAlignedScreenContent(
                title = TITLE,
                supportingText = SUPPORTING_TEXT,
                primaryButton = PRIMARY_BUTTON,
            ),
        )
    }

    @Test
    fun `inequality for differing values`() {
        assertNotEquals(
            LeftAlignedScreenContent(title = TITLE),
            LeftAlignedScreenContent(title = "Different title"),
        )
        assertNotEquals(
            LeftAlignedScreenContent(title = TITLE, primaryButton = PRIMARY_BUTTON),
            LeftAlignedScreenContent(title = TITLE, primaryButton = SECONDARY_BUTTON),
        )
        assertNotEquals(
            LeftAlignedScreenContent(title = TITLE, supportingText = SUPPORTING_TEXT),
            LeftAlignedScreenContent(title = TITLE, supportingText = null),
        )
    }

    companion object {
        private const val TITLE = "Do you have a UK passport?"
        private const val BODY_TEXT = "Body text"
        private const val SUPPORTING_TEXT = "Supporting text"
        private const val PRIMARY_BUTTON = "Primary Button"
        private const val SECONDARY_BUTTON = "Secondary Button"
    }
}
