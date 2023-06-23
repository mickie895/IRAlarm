package io.github.mickie895.iralarm.ui.alarmedit.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.database.AlarmDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmNameEditViewModel @Inject constructor(private val alarmDao: AlarmDao) : ViewModel() {
    fun setNewName(schedule: Schedule, newName: String) {
        viewModelScope.launch {
            alarmDao.setNewName(schedule.alarmId, newName)
        }
    }
}
