package com.example.autoquest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.autoquest.data.database.entity.QuestTaskEntity

@Dao
interface QuestTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestTaskInDataBase(publication: QuestTaskEntity)

    @Query("SELECT * from quest_task")
    suspend fun getAllQuestTaskFromDataBase(): List<QuestTaskEntity>

    @Query("SELECT * from quest_task WHERE quests_id = :questsId")
    suspend fun getDataQuestTask(questsId: Int): QuestTaskEntity
}