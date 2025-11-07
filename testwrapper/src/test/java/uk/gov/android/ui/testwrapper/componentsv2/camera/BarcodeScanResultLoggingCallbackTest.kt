package uk.gov.android.ui.testwrapper.componentsv2.camera

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeSourceStub
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeSourceStub.Companion.asUrlBarcodes
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeSourceStub.Companion.unknown
import uk.gov.android.ui.componentsv2.camera.analyzer.qr.BarcodeSourceStub.Companion.urlQrCode
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeScanResult
import java.io.File
import java.io.FileOutputStream
import java.io.PrintStream


@RunWith(RobolectricTestRunner::class)
@Config(shadows = [ShadowLog::class])
class BarcodeScanResultLoggingCallbackTest {

    @get:Rule
    var folder: TemporaryFolder = TemporaryFolder()

    lateinit var loggingFile: File
    lateinit var fileOutputStream: FileOutputStream
    lateinit var printStream: PrintStream

    @Before
    fun setUp() {
        loggingFile = folder.newFile("barcodeScanResultLoggingCallbackOutputs.txt")
        fileOutputStream = FileOutputStream(loggingFile)
        printStream = PrintStream(fileOutputStream)

        ShadowLog.stream = printStream
    }

    @After
    fun tearDown() {
        fileOutputStream.close()
        printStream.close()
    }

    @Test
    fun emptyScans() = performLoggingFlow(
        result = BarcodeScanResult.EmptyScan,
        expectedMessage = "Barcode data not found"
    )

    @Test
    fun singleUrlScans() = "https://this.is.a.unit.test".run {
        performLoggingFlow(
            result = BarcodeScanResult.Single(urlQrCode(this)),
            expectedMessage = this,
        )
    }

    @Test
    fun singleUnknownScans() = performLoggingFlow(
        result = BarcodeScanResult.Single(unknown()),
        expectedMessage = "No URL found from single result!"
    )

    @Test
    fun exceptions() = "This is a unit test!".run {
        performLoggingFlow(
            result = BarcodeScanResult.Failure(Exception(this)),
            expectedMessage = this
        )
    }

    @Test
    fun successScansPrintTheFirstUrl() = performLoggingFlow(
        result = BarcodeScanResult.Success(
            listOf(
                "https://this.is.a.unit.test",
                "https://this.is.another.test",
            ).asUrlBarcodes()
        ),
        expectedMessage = "https://this.is.a.unit.test"
    ).also {
        assert(
            loggingFile.readLines().none {
                it.contains("https://this.is.another.test")
            }
        )
    }

    @Test
    fun successfulScansWithoutUrls() = performLoggingFlow(
        result = BarcodeScanResult.Success(
            listOf(
                unknown(),
                unknown(),
            )
        ),
        expectedMessage = "No URL found!",
    )

    private fun performLoggingFlow(
        result: BarcodeScanResult,
        expectedMessage: String,
    ) = runTest {
        barcodeScanResultLoggingCallback.onResult(result) {}

        val loggingOutput = loggingFile.readLines()
        assert(
            loggingOutput.any {
                it.contains(
                    "I/BarcodeScanResultLoggingCallback: Barcode scanning result: $expectedMessage"
                )
            }
        ) {
            "Couldn't find the \"$expectedMessage\" in: $loggingOutput"
        }
    }
}