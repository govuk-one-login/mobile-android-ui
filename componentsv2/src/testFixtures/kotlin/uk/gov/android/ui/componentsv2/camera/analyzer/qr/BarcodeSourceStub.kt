package uk.gov.android.ui.componentsv2.camera.analyzer.qr

import android.graphics.Point
import android.graphics.Rect
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource

/**
 * Stub implementation of the [BarcodeSource] interface, used in creating instances of
 * [Barcode].
 *
 * Suppresses `TooManyFunctions` and `CyclomaticComplexMethod` due to the complexity of the
 * [BarcodeSource] interface.
 */
@Suppress("CyclomaticComplexMethod", "TooManyFunctions")
data class BarcodeSourceStub(
    private val format: Int = Barcode.FORMAT_UNKNOWN,
    private val valueType: Int = Barcode.TYPE_UNKNOWN,
    private val boundingBox: Rect? = null,
    private val calendarEvent: Barcode.CalendarEvent? = null,
    private val contactInfo: Barcode.ContactInfo? = null,
    private val driverLicense: Barcode.DriverLicense? = null,
    private val email: Barcode.Email? = null,
    private val geoPoint: Barcode.GeoPoint? = null,
    private val phone: Barcode.Phone? = null,
    private val sms: Barcode.Sms? = null,
    private val urlBookmark: Barcode.UrlBookmark? = null,
    private val wiFi: Barcode.WiFi? = null,
    private val displayValue: String? = null,
    private val rawValue: String? = null,
    private val rawBytes: ByteArray? = null,
    private val cornerPoints: Array<out Point?>? = null,
) : BarcodeSource {
    override fun getFormat(): Int = format
    override fun getValueType(): Int = valueType
    override fun getBoundingBox(): Rect? = boundingBox
    override fun getCalendarEvent(): Barcode.CalendarEvent? = calendarEvent
    override fun getContactInfo(): Barcode.ContactInfo? = contactInfo
    override fun getDriverLicense(): Barcode.DriverLicense? = driverLicense
    override fun getEmail(): Barcode.Email? = email
    override fun getGeoPoint(): Barcode.GeoPoint? = geoPoint
    override fun getPhone(): Barcode.Phone? = phone
    override fun getSms(): Barcode.Sms? = sms
    override fun getUrl(): Barcode.UrlBookmark? = urlBookmark
    override fun getWifi(): Barcode.WiFi? = wiFi
    override fun getDisplayValue(): String? = displayValue
    override fun getRawValue(): String? = rawValue
    override fun getRawBytes(): ByteArray? = rawBytes
    override fun getCornerPoints(): Array<out Point?>? = cornerPoints

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BarcodeSourceStub

        if (format != other.format) return false
        if (valueType != other.valueType) return false
        if (boundingBox != other.boundingBox) return false
        if (calendarEvent != other.calendarEvent) return false
        if (contactInfo != other.contactInfo) return false
        if (driverLicense != other.driverLicense) return false
        if (email != other.email) return false
        if (geoPoint != other.geoPoint) return false
        if (phone != other.phone) return false
        if (sms != other.sms) return false
        if (urlBookmark != other.urlBookmark) return false
        if (wiFi != other.wiFi) return false
        if (displayValue != other.displayValue) return false
        if (rawValue != other.rawValue) return false
        if (!rawBytes.contentEquals(other.rawBytes)) return false
        if (!cornerPoints.contentEquals(other.cornerPoints)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = format
        result = 31 * result + valueType
        result = 31 * result + (boundingBox?.hashCode() ?: 0)
        result = 31 * result + (calendarEvent?.hashCode() ?: 0)
        result = 31 * result + (contactInfo?.hashCode() ?: 0)
        result = 31 * result + (driverLicense?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (geoPoint?.hashCode() ?: 0)
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + (sms?.hashCode() ?: 0)
        result = 31 * result + (urlBookmark?.hashCode() ?: 0)
        result = 31 * result + (wiFi?.hashCode() ?: 0)
        result = 31 * result + (displayValue?.hashCode() ?: 0)
        result = 31 * result + (rawValue?.hashCode() ?: 0)
        result = 31 * result + (rawBytes?.contentHashCode() ?: 0)
        result = 31 * result + (cornerPoints?.contentHashCode() ?: 0)
        return result
    }

    companion object {
        @JvmStatic
        fun unknown(): Barcode = BarcodeSourceStub(
            format = Barcode.FORMAT_UNKNOWN,
            valueType = Barcode.TYPE_UNKNOWN,
        ).let(::Barcode)

        @JvmStatic
        fun urlQrCode(
            url: String,
            title: String = "Barcode Test URL",
        ): Barcode = BarcodeSourceStub(
            format = Barcode.FORMAT_QR_CODE,
            valueType = Barcode.TYPE_URL,
            urlBookmark = Barcode.UrlBookmark(
                title,
                url,
            ),
        ).let(::Barcode)

        fun List<String>.asUrlBarcodes(
            title: String = "Barcode Test URL",
        ): List<Barcode> = map { url ->
            urlQrCode(url = url, title = title)
        }
    }
}
