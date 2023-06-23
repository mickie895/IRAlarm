package io.github.mickie895.iralarm.ui.alarmedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.database.AlarmDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmEditViewModel @Inject constructor(private val alarmDao: AlarmDao) : ViewModel() {
    val alarmList = alarmDao.getScheduleList().asLiveData()

    fun addNewAlarm() {
        viewModelScope.launch {
            alarmDao.addNewSchedule()
        }
    }
}