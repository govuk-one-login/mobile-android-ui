package uk.gov.android.ui.componentsv2.matchers

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher

object SemanticsMatchers {
    fun hasRole(expected: Role) = SemanticsMatcher.expectValue(
        SemanticsProperties.Role,
        expected,
    )
}
