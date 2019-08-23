package com.felipe.test.inloco.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TempInfo(
    val temp: Double? = null,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double
): Parcelable