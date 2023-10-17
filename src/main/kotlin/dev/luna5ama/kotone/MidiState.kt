package dev.luna5ama.kotone

class MidiState private constructor(val array: ByteArray) {
    constructor() : this(ByteArray(128))

    operator fun set(note: Int, velocity: Int) {
        array[note] = velocity.toByte()
    }

    inline fun forEach(action: (note: Int, velocity: Int) -> Unit) {
        for (i in 0..127) {
            val velocity = array[i]
            if (velocity != 0.toByte()) {
                action(i, velocity.toInt())
            }
        }
    }

    fun copy(): MidiState {
        return MidiState(array.copyOf())
    }
}