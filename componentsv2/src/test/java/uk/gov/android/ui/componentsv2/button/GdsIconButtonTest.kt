package uk.gov.android.ui.componentsv2.button

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.R

@RunWith(RobolectricTestRunner::class)
class GdsIconButtonTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private var onClick = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCloseIconButton() {
        composeTestRule.setContent {
            GdsIconButton(
                onClick = { onClick++ },
                content = GdsIconButtonDefaults.defaultCloseContent(),
            )
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(R.string.close_icon_button),
            ).assertIsDisplayed().performClick()
        }
        assertEquals(1, onClick)
    }

    @Test
    fun testBackIconButton() {
        composeTestRule.setContent {
            val content = GdsIconButtonDefaults.defaultCloseContent()
            GdsIconButton(
                onClick = { onClick++ },
                content = content,
            )
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(R.string.close_icon_button),
            ).assertIsDisplayed().performClick()
        }
        assertEquals(1, onClick)
    }

    @Test
    fun testInfoIconButton() {
        composeTestRule.setContent {
            val content = GdsIconButtonDefaults.defaultInfoContent()
            GdsIconButton(
                onClick = { onClick++ },
                content = content,
            )
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(R.string.info_icon_button),
            ).assertIsDisplayed().performClick()
        }
        assertEquals(1, onClick)
    }

    @Test
    fun testMoreIconButton() {
        composeTestRule.setContent {
            val content = GdsIconButtonDefaults.defaultMoreContent()
            GdsIconButton(
                onClick = { onClick++ },
                content = content,
            )
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(R.string.more_vert_icon_button),
            ).assertIsDisplayed().performClick()
        }
        assertEquals(1, onClick)
    }

    @Test
    fun testCustomIconButton() {
        composeTestRule.setContent {
            GdsIconButton(
                onClick = { onClick++ },
                content = IconButtonContent(
                    icon = Icons.Filled.MoreVert,
                    contentDescription = stringResource(R.string.more_vert_icon_button),
                ),
            )
        }
        assertEquals(0, onClick)
        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(R.string.more_vert_icon_button),
            ).assertIsDisplayed().performClick()
        }
        assertEquals(1, onClick)
    }

    @Test
    fun testIconButtonPreview() {
        composeTestRule.setContent {
            GdsIconButtonPreview(GdsIconButtonPreviewProvider().values.first())
        }

        composeTestRule.apply {
            onNodeWithContentDescription(
                context.getString(R.string.back_icon_button),
            ).assertIsDisplayed()
        }
    }
}
