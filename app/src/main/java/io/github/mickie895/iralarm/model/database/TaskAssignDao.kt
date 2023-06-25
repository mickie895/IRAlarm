package io.github.mickie895.iralarm.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.mickie895.iralarm.model.data.IRFormat
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.data.ScheduleAssignment
import kotlinx.coroutines.flow.Flow

/**
 * スケジュールとタスクの割り振りを行うためのDao
 */
@Dao
interface TaskAssignDao {
    @Query("SELECT * FROM Schedule")
    fun getScheduleList(): Flow<List<Schedule>>

    @Query("SELECT * FROM IRFormat")
    fun getIrTaskList(): Flow<List<IRFormat>>

    @Query("SELECT scheduleId FROM ScheduleAssignment WHERE irTaskId = :irTaskId")
    suspend fun getAssignedScheduleFrom(irTaskId: Int): List<Int>

    suspend fun getAssignedScheduleFrom(task: IRFormat): List<Int>
        = getAssignedScheduleFrom(task.taskId)

    @Query("DELETE FROM ScheduleAssignment WHERE scheduleId = :scheduleId")
    suspend fun removeAssign(scheduleId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun assignTask(assignment: ScheduleAssignment)

    suspend fun assignTaskToSchedule(taskId: Int, scheduleId: Int){
        assignTask(ScheduleAssignment(scheduleId, taskId))
    }

    @Insert
    suspend fun insertIRFormat(format:IRFormat)
}