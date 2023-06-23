package io.github.mickie895.iralarm.ui.alarmedit.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.database.AlarmDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmDeleteViewModel @Inject constructor(private val alarmDao: AlarmDao) : ViewModel() {

    fun deleteConfirmed(schedule: Schedule){
        viewModelScope.launch {
            alarmDao.removeSchedule(schedule)
        }
    }
}