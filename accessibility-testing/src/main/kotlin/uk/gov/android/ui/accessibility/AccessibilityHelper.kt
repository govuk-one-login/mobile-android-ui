package uk.gov.android.ui.accessibility

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult.AccessibilityCheckResultType
import com.google.android.apps.common.testing.accessibility.framework.checks.TouchTargetSizeCheck
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator
import org.junit.Assume.assumeTrue
import org.junit.rules.TestRule
import uk.gov.android.ui.accessibility.AccessibilityHelper.accessibilityValidator
import uk.gov.android.ui.accessibility.AccessibilityViewCheckResultMatchers.hasSourceCheckClass

/**
 * Wrapper object containing functions that perform accessibility checks on compose UI.
 */
object AccessibilityHelper {
    /**
     * Default [AccessibilityValidator] that fails tests on both errors and warnings.
     * Performs all applicable accessibility checks, as there's no suppressions in place.
     *
     * Developers can provide their own [AccessibilityValidator] as part of
     * [performAccessibilityCheck].
     *
     * @see performAccessibilityCheck
     */
    private val accessibilityValidator = AccessibilityValidator().apply {
        setThrowExceptionFor(AccessibilityCheckResultType.WARNING)
    }

    /**
     * Runs accessibility checks against the [content], using the [validator].
     * Performs checks on the root compose node, meaning the entire compose hierarchy that's loaded.
     *
     * Accessibility checks can't occur within the unit test suite - they are designed for
     * instrumentation tests.
     *
     * Note that it's assumed that the instrumented device that runs this function is at least
     * [Build.VERSION_CODES.UPSIDE_DOWN_CAKE], due to compose accessibility testing being
     * unavailable on older API levels. When running on older API levels, the tests are skipped via
     * [assumeTrue].
     *
     * See also:
     * - [Accessibility testing in Compose](
     * https://developer.android.com/develop/ui/compose/accessibility/testing
     * )
     *
     * @param content The Compose UI under test.
     * @param validator The [AccessibilityValidator] that checks the [content]. Defaults to
     * [accessibilityValidator].
     * @sample failsAccessibilityChecksSample
     * @sample suppressesAccessibilityChecksSample
     */
    @SuppressLint("ObsoleteSdkInt", "NewApi")
    fun <
        R : TestRule,
        A : ComponentActivity,
        > AndroidComposeTestRule<in R, in A>.performAccessibilityCheck(
        validator: AccessibilityValidator = accessibilityValidator,
        content: @Composable () -> Unit,
    ) {
        assumeTrue(
            "Skipping accessibility test due to API level not being 34+",
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE,
        )
        enableAccessibilityChecks(validator)
        setContent { content() }

        onRoot().tryPerformAccessibilityChecks()
    }

    /**
     * Example structure of an instrumentation test that uses the [performAccessibilityCheck]
     * extension function.
     */
    internal fun failsAccessibilityChecksSample(
        composeTestRule: AndroidComposeTestRule<
            ActivityScenarioRule<ComponentActivity>,
            ComponentActivity,
            >,
        validator: AccessibilityValidator = accessibilityValidator,
    ) {
        composeTestRule.performAccessibilityCheck(validator) {
            // UI under test:
            Button(
                modifier = Modifier.size(16.dp),
                onClick = {},
            ) {
                Text("Fails touch target requirements")
            }
        }
    }

    /**
     * Example structure of an instrumentation test that explicitly suppresses accessibility
     * failures. This may be due to deferring the resolution, or misalignment between UI designs
     * and platform expectations.
     */
    internal fun suppressesAccessibilityChecksSample(
        composeTestRule: AndroidComposeTestRule<
            ActivityScenarioRule<ComponentActivity>,
            ComponentActivity,
            >,
    ) {
        val validator = AccessibilityValidator().apply {
            setSuppressingResultMatcher(
                hasSourceCheckClass(TouchTargetSizeCheck::class),
            )
        }

        composeTestRule.performAccessibilityCheck(validator = validator) {
            // UI under test:
            Button(
                modifier = Modifier.size(16.dp),
                onClick = {},
            ) {
                Text("Fails touch target requirements")
            }
        }
    }
}
