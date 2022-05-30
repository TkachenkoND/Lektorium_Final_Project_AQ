package com.example.autoquest.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestsTasksList(
    @Json(name = "questsTasks")
    val questTaskList: List<QuestTask>
)
