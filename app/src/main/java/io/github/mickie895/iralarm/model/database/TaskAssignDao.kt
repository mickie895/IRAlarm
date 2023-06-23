package io.github.mickie895.iralarm.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.mickie895.iralarm.model.data.ScheduleAssignment
import kotlinx.coroutines.flow.Flow

/**
 * スケジュールとタスクの割り振りを行うためのDao
 */
@Dao
interface TaskAssignDao {
    @Query("SELECT scheduleId FROM ScheduleAssignment WHERE irTaskId = :irTaskId")
    fun getAssignedTasksFrom(irTaskId: Int): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun assignTask(assignment: ScheduleAssignment)

    suspend fun assignTaskToSchedule(taskId: Int, scheduleId: Int){
        assignTask(ScheduleAssignment(scheduleId, taskId))
    }
}