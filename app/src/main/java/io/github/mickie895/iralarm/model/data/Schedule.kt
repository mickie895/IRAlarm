package io.github.mickie895.iralarm.model.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true) val alarmId: Int = 0,
    val alarmName: String = "New Alarm",
    val hour: Int = 12,
    val minute: Int = 0,
    val repeatMonday: Boolean = false,
    val repeatTuesday: Boolean = false,
    val repeatWednesday: Boolean = false,
    val repeatThursday: Boolean = false,
    val repeatFriday: Boolean = false,
    val repeatSaturday: Boolean = false,
    val repeatSunday: Boolean = false,
) {
    @Ignore
    private val weekEnableGenericsArray = arrayOf(
        repeatMonday,
        repeatTuesday,
        repeatWednesday,
        repeatThursday,
        repeatFriday,
        repeatSaturday,
        repeatSunday
    )

    val weekEnableArray get() = BooleanArray(7){i -> weekEnableGenericsArray[i]}

    fun createEnableChangedItem(weekArray: BooleanArray): Schedule {
        if (weekArray.count() != 7) {
            throw IllegalArgumentException("一週間を示す配列が来る必要があるので、配列の長さが7出ない場合は間違った呼び出しになっています。")
        }
        return Schedule(
            alarmId,
            alarmName,
            hour,
            minute,
            weekArray[0],
            weekArray[1],
            weekArray[2],
            weekArray[3],
            weekArray[4],
            weekArray[5],
            weekArray[6]
        )
    }
}
