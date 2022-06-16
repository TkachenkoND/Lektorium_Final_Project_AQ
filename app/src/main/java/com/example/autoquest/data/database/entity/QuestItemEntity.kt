package com.example.autoquest.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quest_item")
data class QuestItemEntity(
    @PrimaryKey()
    @ColumnInfo(name = "quests_id")
    val questsId: Int,

    @ColumnInfo(name = "item_background_img")
    val itemBackgroundImg: String,

    @ColumnInfo(name = "rating")
    val rating: Int,

    @ColumnInfo(name = "quest_description")
    val questDescription: String,

    @ColumnInfo(name = "data_quest")
    val dataQuest: String,

    @ColumnInfo(name = "name_quest")
    val nameQuest: String,

    @ColumnInfo(name = "time_quest")
    val timeQuest: String,

    @ColumnInfo(name = "place_start_quest")
    val placeStartQuest: String,

    @ColumnInfo(name = "img_details_quest")
    val imgDetailsQuest: String,

    @ColumnInfo(name = "access_code")
    val accessCode: String,

)
