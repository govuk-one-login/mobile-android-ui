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
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient(options)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (imageProxy.hasImage()) {
            val inputImage =
                InputImage.fromMediaImage(
                    imageProxy.image!!,
                    imageProxy.imageInfo.rotationDegrees,
                )

            scanner
                .process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    onResult(BarcodeScanResult.Success(barcodeList))
                }.addOnFailureListener {
                    // This failure occurs if the barcode scanning model
                    // fails to download from Google Play Services
                    onResult(BarcodeScanResult.Failure(it))
                }.addOnCompleteListener {
                    // When the image is from CameraX analysis use case, must
                    // call image.close() on received images when finished
                    // using them. Otherwise, the scanner may not receive new images
                    // or the camera may stall.
                    imageProxy.image?.close()
                    imageProxy.close()
                }
        }
    }

    private fun ImageProxy.hasImage(): Boolean = image != null
}
