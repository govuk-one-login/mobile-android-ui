package uk.gov.android.ui.patterns.testutils

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert

object Matchers {
    fun SemanticsNodeInteraction.assertListSemanticsCleared(): SemanticsNodeInteraction =
        this
            .assert(
                SemanticsMatcher.keyIsDefined(SemanticsProperties.CollectionInfo),
            )
            .assert(
                SemanticsMatcher("CollectionInfo has rowCount=0 and columnCount=0") { node ->
                    val collectionInfo = node.config.getOrNull(SemanticsProperties.CollectionInfo)
                    collectionInfo?.rowCount == 0 && collectionInfo.columnCount == 0
                },
            )
}
