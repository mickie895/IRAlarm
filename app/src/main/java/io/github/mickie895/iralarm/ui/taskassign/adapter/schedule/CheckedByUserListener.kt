package io.github.mickie895.iralarm.ui.taskassign.adapter.schedule

import io.github.mickie895.iralarm.model.data.Schedule

interface CheckedByUserListener {
    fun checkedChanged(schedule: Schedule, checked: Boolean)
}