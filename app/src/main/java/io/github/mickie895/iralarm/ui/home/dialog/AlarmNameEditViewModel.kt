package io.github.mickie895.iralarm.ui.home.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.database.AlarmDatabase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmNameEditViewModel @Inject constructor(alarmDatabase: AlarmDatabase) : ViewModel() {
    private val dao = alarmDatabase.alarmDao()
    fun setNewName(schedule: Schedule, newName: String) {
        viewModelScope.launch {
            dao.setNewName(schedule.alarmId, newName)
        }
    }
}
