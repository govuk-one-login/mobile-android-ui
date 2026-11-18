package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Test
import uk.gov.android.ui.componentsv2.button.buttonparameters.ButtonParametersV2
import uk.gov.android.ui.theme.buttonContentHorizontal
import uk.gov.android.ui.theme.buttonContentVertical

class GdsButtonDefaultsContentPaddingTest {
    private val defaultPadding = PaddingValues(
        start = buttonContentHorizontal,
        top = buttonContentVertical,
        end = buttonContentHorizontal,
        bottom = buttonContentVertical,
    )

    @Test
    fun `contentPadding with no arguments uses theme defaults`() {
        assertEquals(defaultPadding, GdsButtonDefaults.contentPadding())
    }

    @Test
    fun `contentPadding for Start position removes only start padding`() {
        assertEquals(
            PaddingValues(
                start = 0.dp,
                top = buttonContentVertical,
                end = buttonContentHorizontal,
                bottom = buttonContentVertical,
            ),
            GdsButtonDefaults.contentPadding(Arrangement.Start),
        )
    }

    @Test
    fun `contentPadding for non-Start positions uses defaults`() {
        listOf(
            Arrangement.Absolute.Center,
            Arrangement.Center,
            Arrangement.End,
        ).forEach {
            assertEquals(defaultPadding, GdsButtonDefaults.contentPadding(it))
        }
    }

    @Test
    fun `contentPadding overrides individual sides and keeps other defaults`() {
        assertEquals(
            PaddingValues(
                start = buttonContentHorizontal,
                top = 4.dp,
                end = 6.dp,
                bottom = buttonContentVertical,
            ),
            GdsButtonDefaults.contentPadding(top = 4.dp, end = 6.dp),
        )
    }

    @Test
    fun `ButtonParametersV2 defaults contentPadding to the position default`() {
        assertEquals(
            GdsButtonDefaults.contentPadding(Arrangement.Start),
            ButtonParametersV2(
                text = "Text",
                buttonType = ButtonTypePreview.Primary,
                contentPosition = Arrangement.Start,
            ).contentPadding,
        )
    }

    @Test
    fun `ButtonParametersV2 toString omits CustomPadding for default padding`() {
        val params = ButtonParametersV2(text = "Text", buttonType = ButtonTypePreview.Primary)

        assertEquals("Primary", params.toString())
    }

    @Test
    fun `ButtonParametersV2 toString omits CustomPadding for default Start padding`() {
        val params = ButtonParametersV2(
            text = "Text",
            buttonType = ButtonTypePreview.Primary,
            contentPosition = Arrangement.Start,
        )

        assertEquals("Primary", params.toString())
    }

    @Test
    fun `ButtonParametersV2 toString appends CustomPadding for custom padding`() {
        val params = ButtonParametersV2(
            text = "Text",
            buttonType = ButtonTypePreview.Primary,
            contentPadding = PaddingValues(0.dp),
        )

        assertEquals("Primary_CustomPadding", params.toString())
    }
}
