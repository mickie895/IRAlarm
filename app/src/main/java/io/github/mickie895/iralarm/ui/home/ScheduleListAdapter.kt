package io.github.mickie895.iralarm.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.github.mickie895.iralarm.R
import io.github.mickie895.iralarm.databinding.LayoutScheduleItemBinding
import io.github.mickie895.iralarm.model.data.Schedule

private val diffUtil = object : DiffUtil.ItemCallback<Schedule>() {
    override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean =
        oldItem.alarmId == newItem.alarmId

    override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean =
        oldItem == newItem
}

class ScheduleListAdapter(private val context: Context): ListAdapter<Schedule, ScheduleListAdapter.ScheduleViewHolder>(diffUtil) {
    class ScheduleViewHolder(private val binding: LayoutScheduleItemBinding, private val context: Context): ViewHolder(binding.root){
        fun bind(schedule: Schedule){
            binding.time.text = context.getString(R.string.time_format, schedule.hour, schedule.minute)
            binding.editAlarmName.setText(schedule.alarmName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(LayoutScheduleItemBinding.inflate(LayoutInflater.from(context), parent, false), context)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}