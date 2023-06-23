package io.github.mickie895.iralarm.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.mickie895.iralarm.model.data.IRData
import io.github.mickie895.iralarm.model.data.IRFormat
import io.github.mickie895.iralarm.model.data.Schedule
import io.github.mickie895.iralarm.model.data.ScheduleAssignment
import javax.inject.Singleton

@Database(entities = [Schedule::class, IRFormat::class, IRData::class, ScheduleAssignment::class], version = 1)
abstract class AlarmDatabase: RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

    abstract fun taskAssignDao(): TaskAssignDao

    abstract fun irTaskDao(): IrTaskDao
}

@Module
@InstallIn(SingletonComponent::class)
object AlarmDatabaseModule{
    @Provides
    @Singleton
    fun provideAlarmDatabase(@ApplicationContext context: Context): AlarmDatabase {
        return Room.databaseBuilder(
            context,
            AlarmDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideAlarmDao(alarmDatabase: AlarmDatabase): AlarmDao
            = alarmDatabase.alarmDao()

    @Provides
    fun provideTaskAssignDao(alarmDatabase: AlarmDatabase): TaskAssignDao
            = alarmDatabase.taskAssignDao()

    @Provides
    fun provideIrTaskDao(alarmDatabase: AlarmDatabase): IrTaskDao
            = alarmDatabase.irTaskDao()
}
