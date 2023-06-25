package io.github.mickie895.iralarm.ui.taskassign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.github.mickie895.iralarm.R
import io.github.mickie895.iralarm.databinding.FragmentIrTaskAssignBinding
import io.github.mickie895.iralarm.model.data.IRFormat
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.ui.taskassign.adapter.ir.FooterAdapter
import io.github.mickie895.iralarm.ui.taskassign.adapter.ir.IrTaskEditListener
import io.github.mickie895.iralarm.ui.taskassign.adapter.ir.TaskSelectAdapter
import io.github.mickie895.iralarm.ui.taskassign.adapter.schedule.CheckableScheduleAdapter
import io.github.mickie895.iralarm.ui.taskassign.adapter.schedule.CheckedByUserListener

@AndroidEntryPoint
class TaskAssignFragment: Fragment(), IrTaskEditListener, CheckedByUserListener {
    private val viewModel : TaskAssignViewModel by viewModels()

    private var _binding: FragmentIrTaskAssignBinding? = null

    private lateinit var taskSelectAdapter: TaskSelectAdapter
    private lateinit var scheduleAdapter: CheckableScheduleAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIrTaskAssignBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // タスク一覧側
        taskSelectAdapter = TaskSelectAdapter(requireContext(), this)
        val footer = FooterAdapter(R.layout.layout_add_ir_task, requireContext()){
            onNewTaskClicked()
        }
        binding.taskSelect.adapter = ConcatAdapter(taskSelectAdapter, footer)

        viewModel.tasks.observe(viewLifecycleOwner){
            taskSelectAdapter.submitNewTaskList(it)
        }

        // アラーム一覧側

        scheduleAdapter = CheckableScheduleAdapter(requireContext(), this)
        binding.scheduleSelect.adapter = scheduleAdapter

        viewModel.schedules.observe(viewLifecycleOwner){
            scheduleAdapter.submitScheduleList(it)
        }

        viewModel.assignedSchedule.observe(viewLifecycleOwner){
            scheduleAdapter.submitCheckedItemList(it)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 新しい赤外線タスクの作成要請
     */
    private fun onNewTaskClicked(){
        viewModel.addNewTask()
    }

    /**
     * 赤外線タスク選択時イベント
     */
    override fun onTaskSelected(task: IRFormat) {
        viewModel.onSelectTask(task)
        scheduleAdapter.resetCheck()
    }

    override fun onTaskEditClicked(task: IRFormat) {
        val action = TaskAssignFragmentDirections.actionNavIrTaskAssignToNavIrTaskEdit(task.taskId)
        findNavController().navigate(action)
    }

    override fun onTaskDeleteClicked(task: IRFormat) {
        TODO("Not yet implemented")
    }

    /**
     * 割当変更イベント
     */
    override fun checkedChanged(schedule: Schedule, checked: Boolean) {
        when (checked) {
            true -> viewModel.setAssignedSchedule(schedule)
            false -> viewModel.removeAssign(schedule)
        }
    }
}