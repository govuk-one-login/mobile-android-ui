package uk.gov.android.ui.accessibility

import androidx.activity.ComponentActivity
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.apps.common.testing.accessibility.framework.checks.TouchTargetSizeCheck
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityViewCheckException
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.accessibility.AccessibilityHelper.failsAccessibilityChecksSample
import uk.gov.android.ui.accessibility.AccessibilityHelper.performAccessibilityCheck
import uk.gov.android.ui.accessibility.AccessibilityHelper.suppressesAccessibilityChecksSample
import uk.gov.android.ui.accessibility.AccessibilityViewCheckResultMatchers.hasSourceCheckClass

@RunWith(AndroidJUnit4::class)
class AccessibilityHelperTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun touchTargetFailureWhenInteractiveElementIsTooSmall() {
        val exception = assertThrows(AccessibilityViewCheckException::class.java) {
            failsAccessibilityChecksSample(composeTestRule)
        }

        assertThat(
            exception.results,
            contains(hasSourceCheckClass(TouchTargetSizeCheck::class)),
        )
    }

    @Test
    fun validatorsWithSuppressionsCanAcceptInaccessibleUi() {
        suppressesAccessibilityChecksSample(composeTestRule)
    }

    @Test
    fun passesTouchTargetChecksWithMinimumSizeConstraints() {
        composeTestRule.performAccessibilityCheck {
            Button(
                modifier = Modifier
                    .minimumInteractiveComponentSize(),
                onClick = {},
            ) {
                Text("Passes")
            }
        }
    }
}
