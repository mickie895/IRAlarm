package io.github.mickie895.iralarm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.mickie895.iralarm.databinding.FragmentHomeBinding
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.ui.home.adapter.AlarmEditListener
import io.github.mickie895.iralarm.ui.home.adapter.ScheduleListAdapter
import io.github.mickie895.iralarm.ui.home.dialog.AlarmDeleteDialog
import io.github.mickie895.iralarm.ui.home.dialog.AlarmNameEditDialog
import io.github.mickie895.iralarm.ui.home.dialog.AlarmTimePickDialog
import io.github.mickie895.iralarm.ui.home.dialog.AlarmWeekEditDialog

@AndroidEntryPoint
class HomeFragment : AlarmEditListener, Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.alarmAddButton.setOnClickListener {
            viewModel.addNewAlarm()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ScheduleListAdapter(requireContext(), this)
        binding.alarmList.adapter = adapter
        viewModel.alarmList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun timeEditClicked(schedule: Schedule) {
        val timePickDialog = AlarmTimePickDialog(schedule)
        timePickDialog.show(parentFragmentManager, "AlarmTimePick")
    }

    override fun deleteClicked(schedule: Schedule) {
        val deleteConfirmDialog = AlarmDeleteDialog(schedule)
        deleteConfirmDialog.show(parentFragmentManager, "AlarmDelete")
    }

    override fun nameEditClicked(schedule: Schedule) {
        val nameEditDialog = AlarmNameEditDialog(schedule)
        nameEditDialog.show(parentFragmentManager, "AlarmNameEdit")
    }

    override fun weekEnableClicked(schedule: Schedule) {
        val weekEditDialog = AlarmWeekEditDialog(schedule)
        weekEditDialog.show(parentFragmentManager, "WeekEnableEdit")
    }
}