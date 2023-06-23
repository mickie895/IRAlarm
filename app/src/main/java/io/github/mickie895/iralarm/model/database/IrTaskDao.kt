package io.github.mickie895.iralarm.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.mickie895.iralarm.model.data.IRData
import io.github.mickie895.iralarm.model.data.IRFormat
import kotlinx.coroutines.flow.Flow

@Dao
interface IrTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveIrTask(irFormat: IRFormat)

    @Query("SELECT * FROM IRData WHERE assignedTask = :taskId ORDER BY `order`")
    fun getTaskList(taskId: Int): Flow<List<IRData>>

    @Delete
    suspend fun removeData(data:IRData)

    @Insert
    suspend fun appendData(data:IRData)
}