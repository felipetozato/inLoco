package com.felipe.test.inloco.model

/**
 * Position class to decouple from Android only object dependencies
 */
data class Position(val lat: Double, val lon: Double)