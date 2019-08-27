package com.felipe.test.inloco.utils

import kotlin.math.roundToInt

object UnitConvertion {

    fun fromKtoC(kelvin: Double) = (kelvin - 273.15).roundToInt()
}