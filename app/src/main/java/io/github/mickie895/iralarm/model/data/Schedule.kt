package io.github.mickie895.iralarm.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @PrimaryKey(autoGenerate = true) val alarmId: Int,
    val alarmName: String,
    val hour: Int,
    val minute: Int,
    val repeatSunday: Boolean,
    val repeatMonday: Boolean,
    val repeatTuesday: Boolean,
    val repeatWednesday: Boolean,
    val repeatThursday: Boolean,
    val repeatFriday: Boolean,
    val repeatSaturday: Boolean,
)
