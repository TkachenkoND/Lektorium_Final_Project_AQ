package com.example.autoquest.domain.models

data class QuestTask(
    val questsId: Int,
    val textTask: String,
    val answerTask: String,
    val accessCode: String,
    val latitude: Double,
    val longitude: Double,
    val questTaskImg: String
)

