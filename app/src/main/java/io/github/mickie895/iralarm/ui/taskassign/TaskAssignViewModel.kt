package io.github.mickie895.iralarm.ui.taskassign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.data.IRFormat
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.data.SignalFormat
import io.github.mickie895.iralarm.model.database.TaskAssignDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskAssignViewModel @Inject constructor(private val taskAssignDao: TaskAssignDao) :
    ViewModel() {
    val schedules = taskAssignDao.getScheduleList().asLiveData()
    val tasks = taskAssignDao.getIrTaskList().asLiveData()
    private val assignedScheduleSource = MutableLiveData<List<Int>>(listOf())
    val assignedSchedule: LiveData<List<Int>> = assignedScheduleSource

    private var selectedTask: IRFormat? = null

    fun onSelectTask(task: IRFormat){
        selectedTask = task
        viewModelScope.launch {
            assignedScheduleSource.postValue(
                taskAssignDao.getAssignedScheduleFrom(task)
            )
        }
    }

    fun removeAssign(schedule: Schedule) {
        viewModelScope.launch {
            taskAssignDao.removeAssign(schedule.alarmId)
        }
    }

    fun setAssignedSchedule(schedule: Schedule) {
        viewModelScope.launch {
            selectedTask?.let {
                taskAssignDao.assignTaskToSchedule(it.taskId, schedule.alarmId)
            }
        }
    }

    fun addNewTask() {
        viewModelScope.launch {
            taskAssignDao.insertIRFormat(IRFormat(0, "New IR Signal", SignalFormat.AEHA, 425))
        }
    }
}
