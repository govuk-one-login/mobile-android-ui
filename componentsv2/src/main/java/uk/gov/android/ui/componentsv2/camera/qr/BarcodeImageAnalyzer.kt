package uk.gov.android.ui.componentsv2.camera.qr

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import kotlinx.coroutines.runBlocking
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter

@OptIn(ExperimentalGetImage::class)
class BarcodeImageAnalyzer(
    options: BarcodeScannerOptions,
    converter: ImageProxyConverter = ImageProxyConverter { null },
    callback: BarcodeScanResult.Callback = BarcodeScanResult.Callback { _, _ -> },
) : ImageAnalysis.Analyzer,
    ImageProxyConverter by converter,
    BarcodeScanResult.Callback by callback {

    private var isScanningEnabled: Boolean = true
    private val scanner = BarcodeScanning.getClient(options)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (isScanningEnabled) {
            handleImageProxy(imageProxy)
        }
    }

    private fun handleImageProxy(imageProxy: ImageProxy) {
        convert(imageProxy)?.let { inputImage ->
            // stop analysing frames until the consumer states they're ready again.
            isScanningEnabled = false
            scanner
                .process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    val validBarcodes = barcodeList.filter {
                        true
                    }

                    if (validBarcodes.isEmpty()) {
                        BarcodeScanResult.EmptyScan
                    } else {
                        BarcodeScanResult.Success(validBarcodes)
                    }.let { result ->
                        runBlocking {
                            onResult(result) {
                                isScanningEnabled = true
                            }
                        }
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
