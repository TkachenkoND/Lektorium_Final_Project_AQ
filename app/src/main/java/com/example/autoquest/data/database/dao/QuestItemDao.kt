package com.example.autoquest.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.autoquest.data.database.entity.QuestItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestItemInDb(questItem: QuestItemEntity)

    @Query("SELECT * from quest_item")
    suspend fun fetchAllQuestItemFromDb(): List<QuestItemEntity>

    @Query("SELECT * from quest_item WHERE quests_id = :questsId")
    suspend fun fetchDataQuestItemFromDb(questsId: Int): QuestItemEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateQuestItemInDb(questItem: QuestItemEntity)
}