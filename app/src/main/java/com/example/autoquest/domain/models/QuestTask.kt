package com.example.autoquest.domain.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestTask(
    val questsId: Int,
    val textTask: String,
    val answerTask: String,
    val accessCode: String,
    val latitude: Double,
    val longitude: Double
)

