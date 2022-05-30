package com.example.autoquest.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestItem(
    val questsId: Int,
    val itemBackgroundImg: String,
    val rating: Int,
    val questDescription: String,
    val dataQuest: String,
    val nameQuest: String,
    val timeQuest: String,
    val placeStartQuest: String,
    val imgDetailsQuest: String,
)
