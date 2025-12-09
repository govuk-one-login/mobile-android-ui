package uk.gov.android.ui.testwrapper.componentsv2.camera

import android.util.Log
import uk.gov.android.ui.componentsv2.camera.qr.BarcodeScanResult

val barcodeScanResultLoggingCallback =
    BarcodeScanResult.Callback { result, toggleScanner ->
        when (result) {
            BarcodeScanResult.EmptyScan -> "Barcode data not found"
            is BarcodeScanResult.Success ->
                result.mapToUrlStrings().firstOrNull() ?: "No URL found!"

            is BarcodeScanResult.Single ->
                result.barcode.url?.url ?: "No URL found from single result!"

            is BarcodeScanResult.Failure -> result.message
        }.let { logMessage ->
            val logTag = "BarcodeScanResultLoggingCallback"
            if (Log.isLoggable(logTag, Log.INFO)) {
                Log.i(logTag, "Barcode scanning result: $logMessage")
            }
        }.also {
            toggleScanner()
        }
    }
