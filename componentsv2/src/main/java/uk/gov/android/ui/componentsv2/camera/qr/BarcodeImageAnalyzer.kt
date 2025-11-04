package uk.gov.android.ui.componentsv2.camera.qr

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import uk.gov.android.ui.componentsv2.camera.ImageProxyConverter

@OptIn(ExperimentalGetImage::class)
class BarcodeImageAnalyzer(
    options: BarcodeScannerOptions,
    converter: ImageProxyConverter = ImageProxyConverter { null },
    callback: BarcodeScanResult.Callback = BarcodeScanResult.Callback {},
) : ImageAnalysis.Analyzer,
    ImageProxyConverter by converter,
    BarcodeScanResult.Callback by callback {

    private val scanner = BarcodeScanning.getClient(options)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        convert(imageProxy)?.let { inputImage ->
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
                    }.let(::onResult)
                }.addOnFailureListener {
                    onResult(BarcodeScanResult.Failure(it))
                }.addOnCompleteListener {
                    imageProxy.close()
                }
        } ?: imageProxy.close()
    }
}
