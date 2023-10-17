package dev.luna5ama.kotone.utils

@JvmInline
value class Vec2f private constructor(val bits: Long) {

    constructor(x: Float, y: Float) : this((x.toRawBits().toLong() shl 32) or (y.toRawBits().toLong() and 0xFFFFFFFF))

    constructor(v: Float) : this(v, v)

    val x: Float
        get() = getX(bits)

    val y: Float
        get() = getY(bits)


    operator fun div(vec2f: Vec2f) = div(vec2f.x, vec2f.y)

    operator fun div(divider: Float) = div(divider, divider)

    fun div(x: Float, y: Float) = Vec2f(this.x / x, this.y / y)


    operator fun times(vec2f: Vec2f) = times(vec2f.x, vec2f.y)

    operator fun times(multiplier: Float) = times(multiplier, multiplier)

    fun times(x: Float, y: Float) = Vec2f(this.x * x, this.y * y)


    operator fun minus(vec2f: Vec2f) = minus(vec2f.x, vec2f.y)

    operator fun minus(value: Float) = minus(value, value)

    fun minus(x: Float, y: Float) = plus(-x, -y)


    operator fun plus(vec2f: Vec2f) = plus(vec2f.x, vec2f.y)

    operator fun plus(value: Float) = plus(value, value)

    fun plus(x: Float, y: Float) = Vec2f(this.x + x, this.y + y)

    operator fun component1() = x

    operator fun component2() = y

    companion object {
        val ZERO = Vec2f(0f, 0f)

        @JvmStatic
        fun getX(bits: Long): Float {
            return Float.fromBits((bits shr 32).toInt())
        }

        @JvmStatic
        fun getY(bits: Long): Float {
            return Float.fromBits((bits and 0xFFFFFFFF).toInt())
        }
    }
}