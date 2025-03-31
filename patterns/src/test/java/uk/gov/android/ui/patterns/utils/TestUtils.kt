package uk.gov.android.ui.patterns.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.test.core.app.ApplicationProvider

object TestUtils {
    fun getString(@StringRes resId: Int): String {
        return ApplicationProvider.getApplicationContext<Context>().getString(resId)
    }

    fun loremIpsum(words: Int): String = LoremIpsum(words).values.joinToString(" ")
}
