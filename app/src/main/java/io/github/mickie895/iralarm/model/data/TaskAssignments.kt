package io.github.mickie895.iralarm.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * アラームのスケジュールと赤外線信号のタスクを紐付けるためのクラス
 *
 * @param scheduleId 起動するスケジュール
 * @param irTaskId スケジュールが起動されたときに実行するタスク
 */
@Entity
data class ScheduleAssignment(
    @PrimaryKey
    val scheduleId: Int,
    val irTaskId: Int
)
