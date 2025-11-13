package uk.gov.android.ui.componentsv2.camera.qr

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter

/**
 * An implementation of [ImageAnalysis.Analyzer] for processing [Barcode]s found from a camera's
 * output.
 *
 * @param options The [BarcodeScannerOptions] that initialises the internal [scanner] property.
 * @param converter The [ImageProxyConverter] transforms provided [ImageProxy] inputs into
 * instances of [InputImage]. Defaults to returning `null`, effectively stopping image analysis
 * from occurring. See the proceeding [ImageProxyConverter.simple] sample for a basic
 * implementation.
 * @param callback The [BarcodeScanResult.Callback] implementation that receives
 * [BarcodeScanResult] sub-classes. Defaults to an empty code block, meaning that nothing occurs
 * during [Task.addOnFailureListener] or [Task.addOnSuccessListener].
 *
 * @sample ImageProxyConverter.simple
 */
@OptIn(ExperimentalGetImage::class)
class BarcodeImageAnalyzer(
    options: BarcodeScannerOptions,
    converter: ImageProxyConverter = ImageProxyConverter { null },
    callback: BarcodeScanResult.Callback = BarcodeScanResult.Callback { _, _ -> },
) : ImageAnalysis.Analyzer,
    ImageProxyConverter by converter,
    BarcodeScanResult.Callback by callback {

    /**
     * The internal flag controlling whether [ImageProxy] inputs are processed.
     * Once an [ImageProxy] starts being processed, this flag is set to `false`.
     *
     * Re-enabling the flag happens via the `toggleScanner` parameter within the
     * [BarcodeScanResult.Callback.onResult] function. This allows consumers to re-enable scanning
     * once additional validation completes.
     */
    private var isScanningEnabled: Boolean = true

    /**
     * The underlying [BarcodeScanner] that finds [Barcode] objects within the [ImageProxy] input.
     */
    private val scanner = BarcodeScanning.getClient(options)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (isScanningEnabled) {
            handleImageProxy(imageProxy)
        }
    }

    /**
     * Performs analysis on the provided [imageProxy], after converting it into an [InputImage].
     */
    private fun handleImageProxy(imageProxy: ImageProxy) {
        convert(imageProxy)?.let { inputImage ->
            // stop analysing frames until the consumer states they're ready again.
            isScanningEnabled = false
            scanner
                .process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    when {
                        barcodeList.size == 1 -> BarcodeScanResult.Single(barcodeList[0])
                        barcodeList.isEmpty() -> BarcodeScanResult.EmptyScan
                        else -> BarcodeScanResult.Success(barcodeList)
                    }.let { result ->
                        onResult(result) { isScanningEnabled = true }
                    }
                }.addOnFailureListener {
                    onResult(BarcodeScanResult.Failure(it)) {
                        isScanningEnabled = true
                    }
                }.addOnCompleteListener {
                    imageProxy.close()
                }
        } ?: imageProxy.close()
    }
}
