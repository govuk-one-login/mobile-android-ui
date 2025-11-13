package uk.gov.android.ui.componentsv2.camera.qr

import com.google.mlkit.vision.barcode.common.Barcode

/**
 * Sealed class representing the different output states emitted by the [BarcodeImageAnalyzer].
 */
sealed class BarcodeScanResult {
    /**
     * The [BarcodeScanResult] state when finding only a single [Barcode] from a camera's input.
     */
    data class Single(val barcode: Barcode) : BarcodeScanResult()

    /**
     * The [BarcodeScanResult] state when finding multiple [Barcode] objects from a camera's input.
     *
     * Implements [Iterable], allowing basic streaming functionality in the underlying [barcodes]
     * property.
     */
    data class Success(
        val barcodes: List<Barcode>,
    ) : BarcodeScanResult(), Iterable<Barcode> by barcodes {
        val size = barcodes.size

        /**
         * Filtration function based on the underlying [Barcode] list.
         *
         * @return A [Single] instance when the [predicate] returns true. Otherwise, an [EmptyScan].
         */
        fun firstBarcodeOrEmpty(
            predicate: (Barcode) -> Boolean,
        ): BarcodeScanResult = barcodes.firstOrNull(predicate)?.let(::Single) ?: EmptyScan

        /**
         * Filtration function based on the [Barcode.UrlBookmark.getUrl] values within the
         * underlying [Barcode] list.
         *
         * @return A [Single] instance when the [predicate] returns true. Otherwise, an [EmptyScan].
         */
        fun firstUrlOrEmpty(
            predicate: (String?) -> Boolean,
        ): BarcodeScanResult = firstBarcodeOrEmpty { predicate(it.url?.url) }

        /**
         * Filtration mechanism that maintains the same data-type (`Success`).
         *
         * @return A new [Success] instance with the sub-list matching the provided [predicate].
         */
        fun filterToSuccess(predicate: (Barcode) -> Boolean): Success = Success(
            barcodes.filter(predicate),
        )

        /**
         * @return A [Success] instance containing only [Barcode] objects that match the provided
         * [type].
         *
         * @see Barcode.TYPE_UNKNOWN
         * @see Barcode.TYPE_CONTACT_INFO
         * @see Barcode.TYPE_EMAIL
         * @see Barcode.TYPE_ISBN
         * @see Barcode.TYPE_PHONE
         * @see Barcode.TYPE_PRODUCT
         * @see Barcode.TYPE_SMS
         * @see Barcode.TYPE_TEXT
         * @see Barcode.TYPE_URL
         * @see Barcode.TYPE_WIFI
         * @see Barcode.TYPE_GEO
         * @see Barcode.TYPE_CALENDAR_EVENT
         * @see Barcode.TYPE_DRIVER_LICENSE
         */
        fun filterByType(type: Int): Success = filterToSuccess { type == it.valueType }

        /**
         * @return A [Success] instance containing only [Barcode] objects that have
         * [Barcode.UrlBookmark.getUrl] values matching the provided [predicate].
         */
        fun filterByUrl(predicate: (String?) -> Boolean): Success = filterToSuccess {
            predicate(it.url?.url)
        }

        /**
         * @return `true` when the underlying [barcodes] property has no elements.
         */
        fun isEmpty(): Boolean = barcodes.isEmpty()

        /**
         * @return A [List] of [Barcode.UrlBookmark.getUrl] values from the underlying [barcodes]
         * property.
         */
        fun mapToUrlStrings(): List<String?> = filterByType(
            Barcode.TYPE_URL,
        ).map { it.url?.url }
    }

    /**
     * The [BarcodeScanResult] state when a failure occurred.
     */
    data class Failure(
        val throwable: Throwable,
    ) : BarcodeScanResult() {
        val message: String? = throwable.message
    }

    /**
     * The [BarcodeScanResult] state when finding no [Barcode]s within a camera's output.
     */
    data object EmptyScan : BarcodeScanResult()

    /**
     * Functional interface for encapsulating (hiding) behaviour on a provided [BarcodeScanResult].
     *
     * @sample BarcodeImageAnalyzer
     */
    fun interface Callback {
        /**
         * @param result The [BarcodeScanResult] to perform behaviour on, such as validation or
         * transformation.
         * @param toggleScanner The lambda call that can re-enable the
         * [androidx.camera.core.ImageAnalysis.Analyzer] implementation calling this function.
         */
        fun onResult(result: BarcodeScanResult, toggleScanner: () -> Unit)
    }
}
