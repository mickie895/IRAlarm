package io.github.mickie895.iralarm.ui.taskassign.adapter.ir

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import io.github.mickie895.iralarm.databinding.LayoutIrTaskSelectItemBinding
import io.github.mickie895.iralarm.model.data.IRFormat

class TaskSelectAdapter(private val context: Context, private val listener: IrTaskEditListener) :
    IrTaskEditListener, ListAdapter<SelectableIrTask, TaskSelectAdapter.TaskViewHolder>(diffUtil) {

    class TaskViewHolder(
        private val binding: LayoutIrTaskSelectItemBinding,
        val context: Context,
        listener: IrTaskEditListener,
        adapter: IrTaskEditListener
    ) : ViewHolder(binding.root) {
        private lateinit var boundFormat: SelectableIrTask

        init {
            binding.taskNameLabel.setOnClickListener {
                listener.onTaskSelected(boundFormat.task)
                adapter.onTaskSelected(boundFormat.task)
            }
            binding.buttonEditTask.setOnClickListener {
                listener.onTaskEditClicked(boundFormat.task)
            }
            binding.irTaskDeleteButton.setOnClickListener {
                listener.onTaskDeleteClicked(boundFormat.task)
            }
        }

        fun bind(irFormat: SelectableIrTask) {
            binding.taskNameLabel.text = irFormat.task.name
            boundFormat = irFormat
            val value = TypedValue()
            context.theme.resolveAttribute(
                when (irFormat.selected) {
                    true -> com.google.android.material.R.attr.colorSecondary
                    false -> com.google.android.material.R.attr.backgroundColor
                }
                ,
                value, true)
            binding.root.setBackgroundResource(value.resourceId)
        }
    }

    private var lastSelectedIrTask: IRFormat? = null
    private var cashedTaskList: List<IRFormat> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutIrTaskSelectItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ),
            context,
            listener,
            this
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun submitNewTaskList(irFormats: List<IRFormat>) {
        cashedTaskList = irFormats
        submitNewList()
    }

    private fun submitNewList(){
        submitList(cashedTaskList.map { SelectableIrTask(it, it.taskId == lastSelectedIrTask?.taskId) })
    }

    override fun onTaskSelected(task: IRFormat) {
        lastSelectedIrTask = task
        submitNewList()
    }

    override fun onTaskEditClicked(task: IRFormat) {
    }

    override fun onTaskDeleteClicked(task: IRFormat) {
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<SelectableIrTask>() {
    override fun areItemsTheSame(oldItem: SelectableIrTask, newItem: SelectableIrTask): Boolean {
        return oldItem.task.taskId == newItem.task.taskId
    }

    override fun areContentsTheSame(oldItem: SelectableIrTask, newItem: SelectableIrTask): Boolean {
        return oldItem == newItem
    }

}