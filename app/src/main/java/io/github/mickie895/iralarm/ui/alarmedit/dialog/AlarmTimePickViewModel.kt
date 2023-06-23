package io.github.mickie895.iralarm.ui.alarmedit.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mickie895.iralarm.model.database.AlarmDao
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmTimePickViewModel @Inject constructor(private val alarmDao: AlarmDao) : ViewModel() {

    fun setNewTime(position: Int, hour: Int, minute: Int) {
        viewModelScope.launch {
            alarmDao.updateTime(position, hour, minute)
        }
    }
}