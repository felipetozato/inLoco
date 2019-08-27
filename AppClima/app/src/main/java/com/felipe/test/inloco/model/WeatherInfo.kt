package com.felipe.test.inloco.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherInfo(
    val main: String? = null,
    val description: String? = null
): Parcelable