package uk.gov.android.ui.componentsv2.list

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class NumberToWordConverterTest(
    private val number: Int,
    private val expectedWord: String,
) {
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Test
    fun verifyConversion() {
        val result = number.convertToWord(context)
        assertEquals(expectedWord, result)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: convert {0} to {1}")
        fun getResult(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(1, "one"),
                arrayOf(2, "two"),
                arrayOf(3, "three"),
                arrayOf(4, "four"),
                arrayOf(5, "five"),
                arrayOf(6, "six"),
                arrayOf(7, "seven"),
                arrayOf(8, "eight"),
                arrayOf(9, "nine"),
                arrayOf(10, "10"),
            )
        }
    }
}
