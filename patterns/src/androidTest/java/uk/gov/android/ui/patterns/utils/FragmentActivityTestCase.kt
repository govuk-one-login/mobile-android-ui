package uk.gov.android.ui.patterns.utils

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule

abstract class FragmentActivityTestCase {
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<TestActivity>()

    protected val context: Context = ApplicationProvider.getApplicationContext()
}
