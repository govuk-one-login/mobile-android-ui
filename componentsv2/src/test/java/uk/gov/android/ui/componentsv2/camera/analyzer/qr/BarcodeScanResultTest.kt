package uk.gov.android.ui.componentsv2.camera.analyzer.qr

import com.google.mlkit.vision.barcode.common.Barcode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeSourceStub.Companion.asUrlBarcodes

@RunWith(RobolectricTestRunner::class)
class BarcodeScanResultTest {
    private val testUrlOne = "https://this.is.a.test"
    private val testUrlTwo = "https://this.is.also.a.test"
    val urlStrings = listOf(testUrlOne, testUrlTwo)
    private val results = urlStrings
        .asUrlBarcodes()
        .let(BarcodeScanResult::Success)

    @Test
    fun sizeIsExposedWithinSuccessObjects() {
        val emptyResults = BarcodeScanResult.Success(emptyList())

        assertTrue(emptyResults.isEmpty())
        assertEquals(
            0,
            emptyResults.size,
        )

        assertFalse(results.isEmpty())
        assertEquals(
            2,
            results.size,
        )
    }

    @Test
    fun provideEmptyScansOnCustomFirstOrEmpty() {
        val result = results.firstUrlOrEmpty { false }

        assertEquals(
            BarcodeScanResult.EmptyScan,
            result,
        )
    }

    @Test
    fun provideSingleResultOnCustomFirstOrEmpty() {
        val result = results.firstUrlOrEmpty { true } as BarcodeScanResult.Single

        assertEquals(
            testUrlOne,
            result.barcode.url?.url,
        )
    }

    @Test
    fun resultsAreFilteredViaIterable() {
        val filteredResults: List<Barcode> = results.filter { false }

        assertTrue(filteredResults.isEmpty())
    }

    @Test
    fun customFiltersProvideSuccessObjects() {
        val filteredResults = results.filterToSuccess { false }

        assertTrue(filteredResults.isEmpty())
    }

    @Test
    fun customFiltrationByType() {
        val urlBarcode = BarcodeSourceStub.urlQrCode(url = testUrlOne)
        val mixedResults = listOf(
            BarcodeSourceStub.unknown(),
            urlBarcode,
        ).let(BarcodeScanResult::Success)

        val filteredResults = mixedResults.filterByType(Barcode.TYPE_URL)

        assertEquals(
            1,
            filteredResults.size,
        )
        assertTrue(urlBarcode in filteredResults)
    }

    @Test
    fun customFiltrationByUrl() {
        val filteredResults = results.filterByUrl { testUrlOne == it }

        assertEquals(
            1,
            filteredResults.size,
        )
    }

    @Test
    fun convertsBarcodeUrlsToStringList() {
        val urls = results.mapToUrlStrings()

        assertEquals(
            urlStrings,
            urls,
        )
    }
}
