package dev.luna5ama.kotone.utils

fun interface FloatFunction {
    operator fun invoke(x: Float): Float
}

fun interface Float2Function {
    operator fun invoke(x: Float, y: Float): Float
}