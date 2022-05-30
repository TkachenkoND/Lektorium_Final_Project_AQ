package com.example.autoquest.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quest_task")
data class QuestTaskEntity(
    @PrimaryKey()
    @ColumnInfo(name = "quests_id")
    val questsId: Int,

    @ColumnInfo(name = "text_task")
    val textTask: String,

    @ColumnInfo(name = "answer_task")
    val answerTask: String,

    @ColumnInfo(name = "access_code")
    val accessCode: String,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double
)
