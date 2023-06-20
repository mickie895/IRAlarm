package io.github.mickie895.iralarm.ui.alarmedit.adapter

import io.github.mickie895.iralarm.model.data.Schedule

interface AlarmEditListener {
    fun timeEditClicked(schedule: Schedule)
    fun deleteClicked(schedule: Schedule)
    fun nameEditClicked(schedule: Schedule)
    fun weekEnableClicked(schedule: Schedule)
}
