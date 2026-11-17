package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsEqualTo
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class GdsButtonContentPaddingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultPaddingIsAppliedWhenNotSpecified() {
        val sizes = measure(
            "implicit" to ButtonConfig(),
            "explicit" to ButtonConfig(contentPadding = GdsButtonDefaults.contentPadding()),
        )

        sizes.getValue("implicit").width
            .assertIsEqualTo(
                sizes.getValue("explicit").width,
                subject = "width",
                tolerance = TOLERANCE,
            )
    }

    @Test
    fun startPositionOmitsStartPadding() {
        val sizes = measure(
            "centre" to ButtonConfig(),
            "start" to ButtonConfig(contentPosition = Arrangement.Start),
        )

        (sizes.getValue("centre").width - sizes.getValue("start").width)
            .assertIsEqualTo(
                HORIZONTAL_PADDING,
                subject = "start padding removed",
                tolerance = TOLERANCE,
            )
    }

    @Test
    fun zeroPaddingRemovesAllHorizontalPadding() {
        val sizes = measure(
            "default" to ButtonConfig(),
            "none" to ButtonConfig(contentPadding = PaddingValues(0.dp)),
        )

        (sizes.getValue("default").width - sizes.getValue("none").width)
            .assertIsEqualTo(
                HORIZONTAL_PADDING * 2,
                subject = "horizontal padding removed",
                tolerance = TOLERANCE,
            )
    }

    @Test
    fun granularOverrideKeepsDefaultsForUnspecifiedSides() {
        val sizes = measure(
            "default" to ButtonConfig(),
            "endOnly" to ButtonConfig(
                contentPadding = GdsButtonDefaults.contentPadding(end = 4.dp),
            ),
        )

        (sizes.getValue("default").width - sizes.getValue("endOnly").width)
            .assertIsEqualTo(
                HORIZONTAL_PADDING - 4.dp,
                subject = "only end padding reduced",
                tolerance = TOLERANCE,
            )
    }

    @Test
    fun verticalPaddingIsOverridable() {
        val sizes = measure(
            "tall" to ButtonConfig(
                contentPadding = GdsButtonDefaults.contentPadding(top = 40.dp, bottom = 40.dp),
            ),
        )

        val height = sizes.getValue("tall").height
        check(height > MIN_INTERACTIVE_HEIGHT) {
            "Expected height > $MIN_INTERACTIVE_HEIGHT but was $height"
        }
    }

    private data class ButtonConfig(
        val contentPosition: Arrangement.Horizontal = Arrangement.Absolute.Center,
        val contentPadding: PaddingValues? = null,
    )

    /**
     * Compose allows a single `setContent` per test, so all variants are rendered together
     * and measured by tag.
     */
    private fun measure(vararg configs: Pair<String, ButtonConfig>): Map<String, DpSize> {
        composeTestRule.setContent {
            Column {
                configs.forEach { (tag, config) ->
                    GdsButton(
                        text = TEXT,
                        buttonType = ButtonTypeV2.Primary(),
                        onClick = {},
                        modifier = Modifier.testTag(tag),
                        contentPosition = config.contentPosition,
                        contentPadding = config.contentPadding
                            ?: GdsButtonDefaults.contentPadding(config.contentPosition),
                    )
                }
            }
        }
        return configs.associate { (tag, _) ->
            val bounds = composeTestRule.onNodeWithTag(tag).getUnclippedBoundsInRoot()
            tag to DpSize(bounds.width, bounds.height)
        }
    }

    private companion object {
        const val TEXT = "Continue"
        val HORIZONTAL_PADDING = 16.dp
        val MIN_INTERACTIVE_HEIGHT = 48.dp
        val TOLERANCE = 1.dp
    }
}
