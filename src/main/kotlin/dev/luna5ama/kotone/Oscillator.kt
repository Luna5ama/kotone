package dev.luna5ama.kotone

import dev.luna5ama.kotone.utils.*
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sin

fun interface Oscillator {
    fun f2(x: Float, freq: Float): Vec2f

    fun f(x: Float, freq: Float): Float {
        return f2(x, freq).x
    }

    operator fun plus(other: Oscillator): Oscillator {
        return Oscillator { x, freq ->
            this.f2(x, freq) + other.f2(x, freq)
        }
    }

    operator fun minus(other: Oscillator): Oscillator {
        return Oscillator { x, freq ->
            this.f2(x, freq) - other.f2(x, freq)
        }
    }

    class Configurator(private val original: Oscillator) {
        var pan = 0.0
        var amp = 1.0
        var phase = 0.0
        val freq = MutableLinearFunc(1.0f, 0.0f)

        private var freqFunc: Float2Function? = null

        fun freq(block: Float2Function) {
            freqFunc = block
        }

        fun build(): Oscillator {
            val fFunc = freqFunc
            return Oscillator { x, f ->
                val panning = pan.toFloat()
                val amplitude = amp.toFloat()
                var newF = freq(f)
                if (fFunc != null) {
                    newF = fFunc(x, newF)
                }
                val v = original.f2(x + phase.toFloat() / newF, newF)
                val l = v.x * clamp(1.0f - panning, 0.0f, 1.0f) + v.y * clamp(-panning, 0.0f, 1.0f)
                val r = v.y * clamp(panning + 1.0f, 0.0f, 1.0f) + v.x * clamp(panning, 0.0f, 1.0f)
                Vec2f(l * amplitude, r * amplitude)
            }
        }
    }

    companion object {
        val SAW = Oscillator { x, freq ->
            val x1 = x * freq
            Vec2f((x1 % 1.0f) * 2.0f - 1.0f)
        }
        val TRIANGLE = Oscillator { x, freq ->
            val x1 = x * freq
            Vec2f(abs((x1 % 1.0f) - 0.5f) * 4.0f - 1.0f)
        }
        val SINE = Oscillator { x, freq ->
            val x1 = x * freq
            Vec2f(sin(x1 * PI_F * 2.0f))
        }
        val SQUARE = Oscillator { x, freq ->
            val x1 = x * freq
            Vec2f(floor((x1 * 2.0f) % 2.0f) * 2.0f - 1.0f)
        }
    }
}

inline operator fun Oscillator.invoke(block: Oscillator.Configurator.() -> Unit): Oscillator {
    return Oscillator.Configurator(this).apply(block).build()
}

fun Iterable<Oscillator>.combined(): Oscillator {
    return this.reduce { acc, oscillator -> acc + oscillator }
}