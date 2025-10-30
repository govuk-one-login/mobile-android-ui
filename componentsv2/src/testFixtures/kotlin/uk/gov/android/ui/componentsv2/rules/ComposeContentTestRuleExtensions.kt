package uk.gov.android.ui.componentsv2.rules

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import uk.gov.android.ui.componentsv2.matchers.SemanticsMatchers.hasRole

object ComposeContentTestRuleExtensions {
    fun ComposeContentTestRule.onAllNodesWithRole(role: Role) =
        onAllNodes(hasRole(role))
    fun ComposeContentTestRule.onNodeWithRole(role: Role) =
        onNode(hasRole(role))
}
