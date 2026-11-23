package uk.gov.android.ui.accessibility

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.apps.common.testing.accessibility.framework.checks.TouchTargetSizeCheck
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityViewCheckException
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.android.ui.accessibility.AccessibilityHelper.performAccessibilityCheck
import uk.gov.android.ui.accessibility.AccessibilityViewCheckResultMatchers.hasSourceCheckClass

@RunWith(AndroidJUnit4::class)
class FailingAccessibilityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun touchTargetFailureWhenInteractiveElementIsTooSmall() = runTest {
        val exception = assertThrows(AccessibilityViewCheckException::class.java) {
            composeTestRule.run {
                performAccessibilityCheck {
                    Button(
                        modifier = Modifier.size(16.dp),
                        onClick = {},
                    ) {
                        Text("Fails")
                    }
                }
            }
        }

        assertThat(
            exception.results,
            contains(hasSourceCheckClass(TouchTargetSizeCheck::class)),
        )
    }

    @Test
    fun passesTouchTargetChecksWithMinimumSizeConstraints() = runTest {
        composeTestRule.run {
            performAccessibilityCheck {
                Button(
                    modifier = Modifier
                        .minimumInteractiveComponentSize(),
                    onClick = {},
                ) {
                    Text("Fails")
                }
            }
        }
    }
}
