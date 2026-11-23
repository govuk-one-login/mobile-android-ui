package uk.gov.android.ui.accessibility

import android.view.View
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheck
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityHierarchyCheck
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityHierarchyCheckResult
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityViewCheckResult
import com.google.android.apps.common.testing.accessibility.framework.ResultMetadata
import com.google.android.apps.common.testing.accessibility.framework.uielement.ViewHierarchyElement
import com.google.android.apps.common.testing.accessibility.framework.utils.contrast.Image
import kotlin.reflect.KClass
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

@Suppress("TooManyFunctions")
object AccessibilityViewCheckResultMatchers {
    fun hasAccessibilityHierarchyCheck(expected: KClass<out AccessibilityHierarchyCheck>) =
        hasAccessibilityHierarchyCheck(expected.java)

    fun hasAccessibilityHierarchyCheck(expected: Class<out AccessibilityHierarchyCheck>) =
        hasAccessibilityHierarchyCheck(equalTo(expected))

    fun hasAccessibilityHierarchyCheck(
        matcher: Matcher<in Class<out AccessibilityHierarchyCheck>>,
    ): Matcher<in AccessibilityViewCheckResult> = AccessibilityViewCheckResultMatcher(matcher) {
        it?.accessibilityHierarchyCheck
    }

    fun hasAccessibilityHierarchyCheckResult(expected: AccessibilityHierarchyCheckResult) =
        hasAccessibilityHierarchyCheckResult(equalTo(expected))

    fun hasAccessibilityHierarchyCheckResult(
        matcher: Matcher<in AccessibilityHierarchyCheckResult>,
    ): Matcher<in AccessibilityViewCheckResult> = AccessibilityViewCheckResultMatcher(matcher) {
        it?.accessibilityHierarchyCheckResult
    }

    fun hasElement(expected: ViewHierarchyElement) = hasElement(equalTo(expected))

    fun hasElement(
        matcher: Matcher<in ViewHierarchyElement>,
    ): Matcher<in AccessibilityViewCheckResult> = AccessibilityViewCheckResultMatcher(matcher) {
        it?.element
    }

    fun hasMessage(expected: CharSequence) = hasMessage(equalTo(expected))

    fun hasMessage(matcher: Matcher<in CharSequence>): Matcher<in AccessibilityViewCheckResult> =
        AccessibilityViewCheckResultMatcher(matcher) {
            it?.message
        }

    fun hasMetadata(expected: ResultMetadata) = hasMetadata(equalTo(expected))

    fun hasMetadata(matcher: Matcher<in ResultMetadata>): Matcher<in AccessibilityViewCheckResult> =
        AccessibilityViewCheckResultMatcher(matcher) {
            it?.metadata
        }

    fun hasResultId(expected: Int) = hasResultId(equalTo(expected))

    fun hasResultId(matcher: Matcher<in Int>): Matcher<in AccessibilityViewCheckResult> =
        AccessibilityViewCheckResultMatcher(matcher) {
            it?.resultId
        }

    fun hasSourceCheckClass(klass: KClass<out AccessibilityCheck>) = hasSourceCheckClass(klass.java)

    fun hasSourceCheckClass(clazz: Class<out AccessibilityCheck>) =
        hasSourceCheckClass(equalTo(clazz))

    fun hasSourceCheckClass(
        matcher: Matcher<in Class<out AccessibilityCheck>>,
    ): Matcher<in AccessibilityViewCheckResult> = AccessibilityViewCheckResultMatcher(matcher) {
        it?.sourceCheckClass
    }

    fun hasSuppressedResultCopy(
        expected: AccessibilityViewCheckResult,
    ): Matcher<in AccessibilityViewCheckResult> = hasSuppressedResultCopy(equalTo(expected))

    fun hasSuppressedResultCopy(
        matcher: Matcher<in AccessibilityViewCheckResult>,
    ): Matcher<in AccessibilityViewCheckResult> = AccessibilityViewCheckResultMatcher(matcher) {
        it?.suppressedResultCopy
    }

    fun hasType(expected: AccessibilityCheckResult.AccessibilityCheckResultType) =
        hasType(equalTo(expected))

    fun hasType(
        matcher: Matcher<in AccessibilityCheckResult.AccessibilityCheckResultType>,
    ): Matcher<in AccessibilityViewCheckResult> = AccessibilityViewCheckResultMatcher(matcher) {
        it?.type
    }

    fun hasView(expected: View): Matcher<in AccessibilityViewCheckResult> =
        hasView(equalTo(expected))

    fun hasView(matcher: Matcher<in View>): Matcher<in AccessibilityViewCheckResult> =
        AccessibilityViewCheckResultMatcher(matcher) {
            it?.view
        }

    fun hasViewImage(expected: Image): Matcher<in AccessibilityViewCheckResult> =
        hasViewImage(equalTo(expected))

    fun hasViewImage(matcher: Matcher<in Image>): Matcher<in AccessibilityViewCheckResult> =
        AccessibilityViewCheckResultMatcher(matcher) {
            it?.viewImage
        }

    private class AccessibilityViewCheckResultMatcher<T>(
        private val matcher: Matcher<in T>,
        private val transformer: (AccessibilityViewCheckResult?) -> T?,
    ) : TypeSafeMatcher<AccessibilityViewCheckResult>() {
        override fun describeTo(description: Description?) = matcher.describeTo(description)
        override fun describeMismatchSafely(
            item: AccessibilityViewCheckResult?,
            mismatchDescription: Description?,
        ) = matcher.describeMismatch(transformer(item), mismatchDescription)

        override fun matchesSafely(item: AccessibilityViewCheckResult?): Boolean =
            matcher.matches(transformer(item))
    }
}
