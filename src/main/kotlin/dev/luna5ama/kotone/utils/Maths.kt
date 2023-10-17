package dev.luna5ama.kotone.utils

import kotlin.math.max
import kotlin.math.min

val Int.isEven: Boolean
    get() = this and 1 == 0

val Int.isOdd: Boolean
    get() = this and 1 == 1

fun clamp(v: Float, min: Float, max: Float) :Float {
    return max(min, min(v, max))
}