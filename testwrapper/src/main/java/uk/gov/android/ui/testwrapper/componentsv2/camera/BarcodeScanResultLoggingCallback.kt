package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.util.Log
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeScanResult

data object BarcodeScanResultLoggingCallback : BarcodeScanResult.Callback {
    override fun onResult(result: BarcodeScanResult, toggleScanner: () -> Unit) {
        when (result) {
            BarcodeScanResult.EmptyScan -> "Barcode data not found"
            is BarcodeScanResult.Success ->
                result.mapToUrlStrings().firstOrNull() ?: "No URL found!"

            is BarcodeScanResult.Single ->
                result.barcode.url?.url ?: "No URL found from single result!"

            is BarcodeScanResult.Failure -> result.message
        }.let { logMessage ->
            Log.i(
                this::class.java.simpleName,
                "Barcode scanning result: $logMessage",
            )
        }.also {
            toggleScanner()
        }
    }
}