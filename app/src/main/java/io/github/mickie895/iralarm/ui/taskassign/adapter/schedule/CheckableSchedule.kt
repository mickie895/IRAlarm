package io.github.mickie895.iralarm.ui.taskassign.adapter.schedule

import io.github.mickie895.iralarm.model.data.Schedule

data class CheckableSchedule(
    val schedule: Schedule,
    val checked: Boolean
)
