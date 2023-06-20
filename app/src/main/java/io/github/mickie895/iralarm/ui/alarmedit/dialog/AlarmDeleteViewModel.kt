package io.github.mickie895.iralarm.ui.alarmedit.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.database.AlarmDatabase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmDeleteViewModel @Inject constructor(database: AlarmDatabase) : ViewModel() {
    private val dao = database.alarmDao()

    fun deleteConfirmed(schedule: Schedule){
        viewModelScope.launch {
            dao.removeSchedule(schedule)
        }
    }
}