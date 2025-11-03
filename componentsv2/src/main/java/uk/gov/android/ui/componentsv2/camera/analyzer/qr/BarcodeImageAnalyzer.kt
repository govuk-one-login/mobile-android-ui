package uk.gov.android.ui.componentsv2.camera.analyzer.qr

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@OptIn(ExperimentalGetImage::class)
class BarcodeImageAnalyzer(
    options: BarcodeScannerOptions,
    private val onResult: (BarcodeScanResult) -> Unit = {},
    private val converter: (ImageProxy) -> InputImage? = { null },
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient(options)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        converter(imageProxy)
            ?.let { inputImage ->
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
                        }.let(onResult::invoke)
                    }.addOnFailureListener {
                        onResult(BarcodeScanResult.Failure(it))
                    }.addOnCompleteListener {
                        imageProxy.close()
                    }
            } ?: imageProxy.close()
    }
}
