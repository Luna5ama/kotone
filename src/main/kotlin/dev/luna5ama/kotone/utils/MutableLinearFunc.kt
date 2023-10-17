package dev.luna5ama.kotone.utils

class MutableLinearFunc(var mul: Float, var add: Float) {
    operator fun plusAssign(v: Float) {
        add += v
    }

    operator fun minusAssign(v: Float) {
        add -= v
    }

    operator fun timesAssign(v: Float) {
        mul *= v
        add *= v
    }

    operator fun divAssign(v: Float) {
        mul /= v
        add /= v
    }

    operator fun plusAssign(v: Double) {
        this.plusAssign(v.toFloat())
    }

    operator fun minusAssign(v: Double) {
        this.minusAssign(v.toFloat())
    }

    operator fun timesAssign(v: Double) {
        this.timesAssign(v.toFloat())
    }

    operator fun divAssign(v: Double) {
        this.divAssign(v.toFloat())
    }

    operator fun invoke(x: Float) = x * mul + add
}