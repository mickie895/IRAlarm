package io.github.mickie895.iralarm.ui.alarmedit.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.mickie895.iralarm.model.data.Schedule

/**
 * アラーム起動時間の設定
 */
@AndroidEntryPoint
class AlarmTimePickDialog(private val schedule: Schedule) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    private val viewModel: AlarmTimePickViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, schedule.hour, schedule.minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        viewModel.setNewTime(schedule.alarmId, hourOfDay, minute)
    }
}