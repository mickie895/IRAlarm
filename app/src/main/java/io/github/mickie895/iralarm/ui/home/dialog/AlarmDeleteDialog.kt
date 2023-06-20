package io.github.mickie895.iralarm.ui.home.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.mickie895.iralarm.R
import io.github.mickie895.iralarm.model.data.Schedule

@AndroidEntryPoint
class AlarmDeleteDialog(private val schedule: Schedule) : DialogFragment() {
    private val viewModel: AlarmDeleteViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).apply {
            this.setPositiveButton(R.string.button_ok) { _, _ ->
                viewModel.deleteConfirmed(schedule)
            }
            this.setNegativeButton(R.string.button_cansel) { _, _ -> }
            this.setMessage(getString(R.string.delete_alarm_confirm, schedule.alarmName))
        }.create()
    }
}