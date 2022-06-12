package com.example.autoquest.domain.models

data class QuestItem(
    val questsId: Int,
    val itemBackgroundImg: String,
    val rating: Int,
    val isFavorite: Boolean,
    val questDescription: String,
    val dataQuest: String,
    val nameQuest: String,
    val timeQuest: String,
    val placeStartQuest: String,
    val imgDetailsQuest: String,
    val accessCode: String
)
