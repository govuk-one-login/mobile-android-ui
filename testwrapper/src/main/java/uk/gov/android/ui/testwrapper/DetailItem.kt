package uk.gov.android.ui.testwrapper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
class DetailItem(
    val label: String,
    val name: String,
) : Parcelable
