package io.github.mickie895.iralarm.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.mickie895.iralarm.model.data.Schedule
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {
    @Query("SELECT * FROM Schedule")
    fun getScheduleList(): Flow<List<Schedule>>

    @Insert
    suspend fun addNewSchedule(schedule: Schedule = Schedule())

    @Delete
    suspend fun removeSchedule(schedule: Schedule)

    @Update
    suspend fun saveSchedule(schedule: Schedule)

    @Query("UPDATE Schedule SET alarmName = :newName WHERE alarmId = :alarmId ")
    suspend fun setNewName(alarmId: Int, newName: String)

    @Query("UPDATE Schedule SET hour = :hour, minute = :minute WHERE alarmId = :alarmId ")
    suspend fun updateTime(alarmId: Int, hour: Int, minute: Int)
}
