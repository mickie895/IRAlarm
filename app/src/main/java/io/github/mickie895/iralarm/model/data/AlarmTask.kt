package io.github.mickie895.iralarm.model.data

import androidx.room.Entity

@Entity
data class AlarmTask(
    val scheduleId: Int,
    val irTask: IRTask,
    val taskNumber: Int
)
