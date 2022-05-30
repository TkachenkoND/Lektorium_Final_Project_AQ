package com.example.autoquest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.autoquest.data.database.entity.QuestItemEntity

@Dao
interface QuestItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestItemInDataBase(publication: QuestItemEntity)

    @Query("SELECT * from quest_item")
    suspend fun getAllQuestItemFromDataBase(): List<QuestItemEntity>

    @Query("SELECT * from quest_item WHERE quests_id = :questsId")
    suspend fun getDataQuestItem(questsId: Int): QuestItemEntity
}