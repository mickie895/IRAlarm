package io.github.mickie895.iralarm.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class SignalFormat{
    NEC, AEHA
}

@Entity
data class IRTask(
    @PrimaryKey(autoGenerate = true) val taskId: Int,
    val name: String,
    val format: SignalFormat,
    val data : Array<Byte>,
    val t: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IRTask

        if (taskId != other.taskId) return false
        if (name != other.name) return false
        if (format != other.format) return false
        if (!data.contentEquals(other.data)) return false
        if (t != other.t) return false

        return true
    }

    override fun hashCode(): Int {
        var result = taskId
        result = 31 * result + name.hashCode()
        result = 31 * result + format.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + t
        return result
    }
}
