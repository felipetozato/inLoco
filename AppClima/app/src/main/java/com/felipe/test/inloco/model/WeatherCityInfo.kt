package com.felipe.test.inloco.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherCityInfo(
    val name: String? = null,
    @SerializedName("main") val tempInfo: TempInfo? = null,
    @SerializedName("weather") val weatherCityInfo: List<WeatherInfo>? = null
) : Parcelable