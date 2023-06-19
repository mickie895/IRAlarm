package io.github.mickie895.iralarm.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true) val alarmId: Int = 0,
    val alarmName: String = "New Alarm",
    val hour: Int = 12,
    val minute: Int = 0,
    val repeatSunday: Boolean = false,
    val repeatMonday: Boolean = false,
    val repeatTuesday: Boolean = false,
    val repeatWednesday: Boolean = false,
    val repeatThursday: Boolean = false,
    val repeatFriday: Boolean = false,
    val repeatSaturday: Boolean = false,
)
