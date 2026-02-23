package uk.gov.android.ui.patterns.utils

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.semantics

/**
 * To be used on [Modifier] objects for [LazyColumn] usages.
 *
 * Needed to stop TalkBack from announcing details about the contained
 * elements being "in list" as well as the number of items.
 * For this to work, the rowCount and columnCount need to be set to 0.
 */
fun Modifier.clearListSemanticsForTalkBack(): Modifier =
    this.semantics {
        collectionInfo = CollectionInfo(
            rowCount = 0,
            columnCount = 0,
        )
    }
