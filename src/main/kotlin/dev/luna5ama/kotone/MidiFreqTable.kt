package dev.luna5ama.kotone

import kotlin.math.pow

object MidiFreqTable {
    private val table = FloatArray(128)

    operator fun get(note: Int): Float {
        return table[note]
    }

    init {
        for (i in 0..127) {
            table[i] = (440.0 * 2.0.pow((i - 69) / 12.0)).toFloat()
        }
    }
}