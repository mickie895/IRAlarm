package io.github.mickie895.iralarm.ui.taskassign.adapter.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.github.mickie895.iralarm.databinding.LayoutScheduleSelectionItemBinding
import io.github.mickie895.iralarm.model.data.Schedule

class CheckableScheduleAdapter(
    private val context: Context,
    private val listener: CheckedByUserListener
) : ListAdapter<CheckableSchedule, CheckableScheduleAdapter.CheckedScheduleViewHolder>(
    diffUtil
) {
    class CheckedScheduleViewHolder(
        private val binding: LayoutScheduleSelectionItemBinding,
        listener: CheckedByUserListener
    ) : ViewHolder(binding.root) {
        private lateinit var boundSchedule: Schedule

        // コードによってチェック状態を更新されたときにイベントが出ないようにするためのフラグ
        private var canTriggerEvent: Boolean = false

        init {
            binding.scheduleSelectCheck.setOnCheckedChangeListener { _, checked ->
                if (canTriggerEvent) {
                    listener.checkedChanged(boundSchedule, checked)
                }
            }
        }

        fun bind(content: CheckableSchedule) {
            canTriggerEvent = false
            boundSchedule = content.schedule
            binding.scheduleSelectCheck.text = content.schedule.alarmName
            binding.scheduleSelectCheck.isChecked = content.checked
            canTriggerEvent = true
        }
    }

    private var scheduleListSource: List<Schedule> = listOf()
    private var checkedScheduleSource: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckedScheduleViewHolder {
        return CheckedScheduleViewHolder(
            LayoutScheduleSelectionItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: CheckedScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitScheduleList(schedules: List<Schedule>) {
        scheduleListSource = schedules
        checkedScheduleSource = listOf()
        reloadCheckItems()
    }

    /**
     * チェック対象の更新
     */
    fun submitCheckedItemList(scheduleIds: List<Int>) {
        checkedScheduleSource = scheduleIds
        reloadCheckItems()
    }

    private fun reloadCheckItems() {
        submitList(scheduleListSource.map {
            CheckableSchedule(it, checkedScheduleSource.contains(it.alarmId))
        })
    }

    fun resetCheck() {
        submitCheckedItemList(listOf())
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<CheckableSchedule>() {
    override fun areItemsTheSame(oldItem: CheckableSchedule, newItem: CheckableSchedule): Boolean {
        return oldItem.schedule.alarmId == newItem.schedule.alarmId
    }

    override fun areContentsTheSame(
        oldItem: CheckableSchedule,
        newItem: CheckableSchedule
    ): Boolean {
        return oldItem == newItem
    }

}
