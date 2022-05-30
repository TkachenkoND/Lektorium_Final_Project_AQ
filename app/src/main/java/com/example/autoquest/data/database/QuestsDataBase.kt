package com.example.autoquest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.data.database.dao.QuestTaskDao
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.data.database.entity.QuestTaskEntity

@Database(entities = [QuestItemEntity::class, QuestTaskEntity::class], version = 1)
abstract class QuestsDataBase : RoomDatabase() {
    abstract fun getQuestItemDao(): QuestItemDao
    abstract fun getQuestTaskDao(): QuestTaskDao

}