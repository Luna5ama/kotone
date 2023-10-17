package dev.luna5ama.kotone.utils

import kotlin.math.pow

@JvmInline
value class Hz(val hz: Float) {
    operator fun times(mul: Float) = Hz(hz * mul)
    operator fun plus(other: Hz) = Hz(hz + other.hz)
    operator fun plus(other: Cent) = Hz(hz * 2.0f.pow(other.v / 1200.0f))
    operator fun plus(other: Octave) = Hz(hz * 2.0f.pow(other.v))
    operator fun minus(other: Hz) = Hz(hz - other.hz)
    operator fun minus(other: Cent) = Hz(hz / 2.0f.pow(other.v / 1200.0f))
    operator fun minus(other: Octave) = Hz(hz / 2.0f.pow(other.v))
}

@JvmInline
value class Cent(val v: Float) {
    val octave: Octave
        get() = Octave(v / 1200.0f)

    operator fun plus(cent: Int) = Cent(this.v + cent)
    operator fun plus(cent: Float) = Cent(this.v + cent)
    operator fun plus(other: Cent) = this + other.v
    operator fun plus(other: Octave) = this + other.cent

    operator fun minus(cent: Int) = Cent(this.v - cent)
    operator fun minus(cent: Float) = Cent(this.v - cent)
    operator fun minus(other: Cent) = this - other.v
    operator fun minus(other: Octave) = this - other.cent

    operator fun times(mul: Float) = Cent(v * mul)
}

@JvmInline
value class Octave(val v: Float) {
    val cent: Cent
        get() = Cent(v * 1200.0f)

    operator fun plus(octave: Int) = Octave(this.v + octave)
    operator fun plus(octave: Float) = Octave(this.v + octave)
    operator fun plus(other: Octave) = this + other.v
    operator fun plus(other: Cent) = this + other.octave

    operator fun minus(octave: Int) = Octave(this.v - octave)
    operator fun minus(octave: Float) = Octave(this.v - octave)
    operator fun minus(other: Octave) = this - other.v
    operator fun minus(other: Cent) = this - other.octave
}

val Int.hz: Hz
    get() = Hz(this.toFloat())

val Float.hz: Hz
    get() = Hz(this)

val Int.cent: Cent
    get() = Cent(this.toFloat())

val Float.cent: Cent
    get() = Cent(this)

val Int.octave: Octave
    get() = Octave(this.toFloat())

val Float.octave: Octave
    get() = Octave(this)
