package uk.gov.android.ui.componentsv2.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composer
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.lang.reflect.Method
import java.lang.reflect.Modifier as ReflectModifier
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.GraphicsMode

/**
 * Tests the `DeprecationLevel.HIDDEN` GdsButton overload that was kept for binary compatibility
 * when `contentPadding` was added. It cannot be called from Kotlin source, so it is invoked via
 * reflection on the generated `GdsButtonKt` class. It is the only public `GdsButton` overload with
 * 16 JVM parameters (the name is mangled because of [TextAlign]).
 */
@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class GdsButtonContentPaddingOverloadTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val buttonType = ButtonTypeV2.Primary()
    private val textAlign = TextAlign.Center.value
    private val shape = GdsButtonDefaults.defaultShape
    private val position = Arrangement.Absolute.Center
    private val icon = ButtonIcon(
        icon = ImageVector.Builder(
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).build(),
        contentDescription = "icon",
    )

    @Test
    fun overloadWithoutContentPadding_rendersAndClicks() {
        var clicks = 0
        setContentWithOverload { composer ->
            arrayOf(
                TEXT,
                buttonType,
                { clicks++ },
                Modifier.testTag(TAG),
                Modifier,
                position,
                true,
                false,
                textAlign,
                shape,
                icon,
                MutableInteractionSource(),
                composer,
                0,
                0,
                0,
            )
        }

        composeTestRule.onNodeWithTag(TAG).assertIsEnabled().performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun overloadWithoutContentPadding_disabledIsNotEnabled() {
        setContentWithOverload { composer ->
            arrayOf(
                TEXT,
                buttonType,
                { },
                Modifier.testTag(TAG),
                Modifier,
                position,
                false,
                false,
                textAlign,
                shape,
                null,
                MutableInteractionSource(),
                composer,
                0,
                0,
                0,
            )
        }

        composeTestRule.onNodeWithTag(TAG).assertIsNotEnabled()
    }

    // The last int is Compose's `$default` bitmask: bit N set = use the default for parameter N.
    // The passed values for those parameters are ignored, but must still be non-null.
    @Test
    fun overloadWithoutContentPadding_usesDefaults() {
        var clicks = 0
        setContentWithOverload { composer ->
            arrayOf(
                TEXT,
                buttonType,
                { clicks++ },
                Modifier,
                Modifier,
                position,
                true,
                false,
                textAlign,
                shape,
                icon,
                MutableInteractionSource(),
                composer,
                0,
                0,
                DEFAULTS_MASK,
            )
        }

        composeTestRule.onNodeWithText(TEXT).assertIsEnabled().performClick()
        assertEquals(1, clicks)
    }

    @OptIn(InternalComposeApi::class)
    private fun setContentWithOverload(args: (Composer) -> Array<Any?>) {
        val method = findOverload()
        composeTestRule.setContent {
            method.invoke(null, *args(currentComposer))
        }
    }

    private fun findOverload(): Method {
        val method = Class.forName("uk.gov.android.ui.componentsv2.button.GdsButtonKt")
            .declaredMethods
            .firstOrNull {
                it.name.startsWith("GdsButton") &&
                    ReflectModifier.isPublic(it.modifiers) &&
                    it.parameterCount == PARAMETER_COUNT
            }
        assertNotNull("No GdsButton overload with $PARAMETER_COUNT parameters", method)
        return method!!
    }

    private companion object {
        const val TEXT = "Button"
        const val TAG = "button"
        const val PARAMETER_COUNT = 16

        // Parameters 3..11 (modifier .. interactionSource) all use their default values
        const val DEFAULTS_MASK = 0b1111_1111_1000
    }
}
