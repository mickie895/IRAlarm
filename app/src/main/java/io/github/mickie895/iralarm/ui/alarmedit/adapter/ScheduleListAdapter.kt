package io.github.mickie895.iralarm.ui.alarmedit.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.github.mickie895.iralarm.R
import io.github.mickie895.iralarm.databinding.LayoutScheduleItemBinding
import io.github.mickie895.iralarm.model.data.Schedule
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

private val diffUtil = object : DiffUtil.ItemCallback<Schedule>() {
    override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean =
        oldItem.alarmId == newItem.alarmId

    override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean =
        oldItem == newItem
}

class ScheduleListAdapter(private val context: Context, private val listener: AlarmEditListener) :
    ListAdapter<Schedule, ScheduleListAdapter.ScheduleViewHolder>(
        diffUtil
    ) {


    class ScheduleViewHolder(
        private val binding: LayoutScheduleItemBinding,
        private val context: Context,
        listener: AlarmEditListener
    ) : ViewHolder(binding.root) {
        private lateinit var schedule: Schedule

        companion object {
            @SuppressLint("ConstantLocale")
            private val weekLabels =
                Locale.getDefault().run {
                    DayOfWeek.values().map {
                        it.getDisplayName(TextStyle.SHORT, this)
                    }.toTypedArray()
                }
        }

        init {
            binding.buttonTimeEdit.setOnClickListener {
                listener.timeEditClicked(schedule)
            }

            binding.buttonDelete.setOnClickListener {
                listener.deleteClicked(schedule)
            }

            binding.buttonNameEdit.setOnClickListener {
                listener.nameEditClicked(schedule)
            }

            binding.buttonEditRepeat.setOnClickListener {
                listener.weekEnableClicked(schedule)
            }
        }

        fun bind(schedule: Schedule) {
            this.schedule = schedule
            binding.time.text =
                context.getString(R.string.time_format, schedule.hour, schedule.minute)
            binding.editAlarmName.text = schedule.alarmName
            binding.repeat.text = if (schedule.weekEnableArray.all { b -> !b })
                context.getString(R.string.no_repeat)
            else
                weekLabels.filterIndexed { dayOfWeek, _ -> schedule.weekEnableArray[dayOfWeek] }
                    .joinToString(separator = ", ")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
            LayoutScheduleItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ), context, listener
        )
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}