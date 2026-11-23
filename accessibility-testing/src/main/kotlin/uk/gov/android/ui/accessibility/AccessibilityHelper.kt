package uk.gov.android.ui.accessibility

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult.AccessibilityCheckResultType
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator
import org.junit.Assume.assumeTrue
import org.junit.rules.TestRule

object AccessibilityHelper {
    val accessibilityValidator = AccessibilityValidator().apply {
        setThrowExceptionFor(AccessibilityCheckResultType.WARNING)
    }

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
}
