package io.github.mickie895.iralarm.ui.home.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.mickie895.iralarm.R
import io.github.mickie895.iralarm.model.data.Schedule
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@AndroidEntryPoint
class AlarmWeekEditDialog(private val schedule: Schedule) : DialogFragment() {
    private val weekLabels =
        Locale.getDefault().run {
            DayOfWeek.values().map {
                it.getDisplayName(TextStyle.FULL, this)
            }.toTypedArray()
        }

    private val weekEnables = schedule.weekEnableArray

    private val choiceClickedListener =
        DialogInterface.OnMultiChoiceClickListener { _, itemPosition, checked ->
            weekEnables[itemPosition] = checked
        }

    private val viewModel: AlarmWeekEditViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).apply {
            setMultiChoiceItems(weekLabels, schedule.weekEnableArray, choiceClickedListener)
            setPositiveButton(R.string.button_ok) { _, _ ->
                viewModel.setNewWeekDayItem(schedule, weekEnables)
            }
            setNegativeButton(R.string.button_cansel) { _, _ -> }
        }.create()
    }
}