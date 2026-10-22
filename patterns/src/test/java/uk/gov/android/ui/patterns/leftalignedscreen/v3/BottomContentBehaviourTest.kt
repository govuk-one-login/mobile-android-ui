package uk.gov.android.ui.patterns.leftalignedscreen.v3

import org.junit.Assert.assertEquals
import org.junit.Test

class BottomContentBehaviourTest {
    @Test
    fun `contains expected entries`() {
        val expected =
            setOf(
                BottomContentBehaviour.Fixed,
                BottomContentBehaviour.Adaptive,
            )
        assertEquals(expected, BottomContentBehaviour.entries.toSet())
    }
}
