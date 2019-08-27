package com.felipe.test.inloco.features.pushnotif.model

import com.google.gson.annotations.SerializedName

class PushInformaionContent(
    val title: String,
    val message: String,
    @SerializedName("icon_url") val iconUrl: String
)