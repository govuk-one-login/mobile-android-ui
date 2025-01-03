package uk.gov.android.ui.components.content

import android.os.Parcelable
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class GdsContentText(
    @StringRes open val subTitle: Int?,
    @StringRes open val subTitle2: Int?,
    open val subTitle2Var: String? = null,
) : Parcelable {

    @Parcelize
    data class GdsContentTextString(
        @StringRes val text: IntArray,
        val textVar: String? = null,
        @StringRes override val subTitle: Int? = null,
        @StringRes override val subTitle2: Int? = null,
        override val subTitle2Var: String? = null,
    ) : GdsContentText(
        subTitle = subTitle,
        subTitle2 = subTitle2,
        subTitle2Var = subTitle2Var,
    ),
        Parcelable {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as GdsContentTextString
            if (text.contentEquals(other.text)) return false
            if (subTitle != other.subTitle) return false
            if (subTitle2 != other.subTitle2) return false
            return true
        }

        override fun hashCode(): Int {
            var result = subTitle ?: 0
            result = 31 * result + text.contentHashCode()
            return result
        }
    }

    @Parcelize
    data class GdsContentTextArray(
        @StringRes override val subTitle: Int? = null,
        @StringRes override val subTitle2: Int? = null,
        @ArrayRes val text: Int,
    ) : GdsContentText(subTitle = subTitle, subTitle2 = subTitle2), Parcelable
}
