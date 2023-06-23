package io.github.mickie895.iralarm.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class SignalFormat{
    NEC, AEHA
}

/**
 * 赤外線信号のフォーマット
 *
 * @param taskId
 * 赤外線送信のID、連携用。
 * @param name
 * タスク全体の名称
 * @param format
 * AEHAやNECなどのフォーマット
 * @param t
 * 変調単位
 */
@Entity
data class IRFormat(
    @PrimaryKey(autoGenerate = true) val taskId: Int,
    val name: String,
    val format: SignalFormat,
    val t: Int
)

/**
 * リモコンの信号として送信するデータ
 * @param dataId データ自体の識別用ID
 * @param assignedTask このデータが所属しているタスク
 * @param order タスク内の順番
 * @param data 実際に送るデータ
 * @param interval 次の信号を送るまでの待機時間（秒）
 */
@Entity
data class IRData(
    @PrimaryKey(autoGenerate = true) val dataId: Int,
    val assignedTask: Int,
    val order: Int,
    val data: ByteArray,
    val interval: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IRData

        if (dataId != other.dataId) return false
        if (!data.contentEquals(other.data)) return false
        if (interval != other.interval) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dataId
        result = 31 * result + data.contentHashCode()
        result = 31 * result + interval
        return result
    }
}