package io.github.mickie895.iralarm.ui.alarmedit.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.mickie895.iralarm.R
import io.github.mickie895.iralarm.databinding.DialogNameEditBinding
import io.github.mickie895.iralarm.model.data.Schedule

@AndroidEntryPoint
class AlarmNameEditDialog(private val schedule: Schedule) : DialogFragment() {
    private val alarmNameEditViewModel: AlarmNameEditViewModel by viewModels()

    private lateinit var binding: DialogNameEditBinding

    private val editListener = DialogInterface.OnClickListener { _, _ ->
        binding.nameEditText.text.run {
            if (this.isNotBlank()) {
                alarmNameEditViewModel.setNewName(schedule, this.toString())
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNameEditBinding.inflate(requireActivity().layoutInflater)
        binding.nameEditText.setText(schedule.alarmName)
        return AlertDialog.Builder(requireContext()).apply {
            this.setView(binding.root)
            this.setPositiveButton(R.string.button_ok, editListener)
            this.setNegativeButton(R.string.button_cansel) { _, _ -> }
            this.setTitle(R.string.hint_alarm_name_edit)
        }.create()
    }
}