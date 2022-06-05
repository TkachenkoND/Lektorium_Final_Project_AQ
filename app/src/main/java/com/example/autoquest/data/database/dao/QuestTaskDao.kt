package com.example.autoquest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.data.database.entity.QuestTaskEntity
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestTask
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestTaskInDataBase(questsTasks: QuestTaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestItemInDataBase(questsItems: QuestItemEntity)

    @Query("SELECT * from quest_task WHERE quests_id = :questsId")
    suspend fun getDataQuestTask(questsId: Int): QuestTaskEntity

    @Query("SELECT * from quest_item WHERE quests_id = :questsId")
    suspend fun getDataQuestItem(questsId: Int): QuestItemEntity
}