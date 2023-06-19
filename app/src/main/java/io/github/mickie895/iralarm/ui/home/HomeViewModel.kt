package io.github.mickie895.iralarm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.database.AlarmDatabase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(alarmDatabase: AlarmDatabase) : ViewModel() {
    private val alarmDao = alarmDatabase.alarmDao()
    val alarmList = alarmDao.getScheduleList().asLiveData()

    fun addNewAlarm() {
        viewModelScope.launch {
            alarmDao.addNewSchedule()
        }
    }

}