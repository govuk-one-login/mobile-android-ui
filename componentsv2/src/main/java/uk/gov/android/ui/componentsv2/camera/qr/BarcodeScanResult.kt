package uk.gov.android.ui.componentsv2.camera.qr

import com.google.mlkit.vision.barcode.common.Barcode

sealed class BarcodeScanResult {
    data class Single(val barcode: Barcode) : BarcodeScanResult()

    data class Success(
        val barcodes: List<Barcode>,
    ) : BarcodeScanResult(), Iterable<Barcode> by barcodes {
        val size = barcodes.size

        fun firstBarcodeOrEmpty(
            predicate: (Barcode) -> Boolean,
        ): BarcodeScanResult = barcodes.firstOrNull(predicate)?.let(::Single) ?: EmptyScan

        fun firstUrlOrEmpty(
            predicate: (String?) -> Boolean,
        ): BarcodeScanResult = firstBarcodeOrEmpty { predicate(it.url?.url) }

        fun filterToSuccess(predicate: (Barcode) -> Boolean): Success = Success(
            barcodes.filter(predicate),
        )

        /**
         * @see Barcode.TYPE_URL
         */
        fun filterByType(type: Int): Success = filterToSuccess { type == it.valueType }

        fun filterByUrl(predicate: (String?) -> Boolean): Success = filterToSuccess {
            predicate(it.url?.url)
        }

        fun isEmpty(): Boolean = barcodes.isEmpty()

        fun mapToUrlStrings(): List<String?> = filterByType(
            Barcode.TYPE_URL,
        ).map { it.url?.url }
    }

    data class Failure(
        val e: Throwable,
    ) : BarcodeScanResult() {
        val message: String? = e.message
    }

    data object EmptyScan : BarcodeScanResult()

    fun interface Callback {
        fun onResult(result: BarcodeScanResult)
    }
}
