package com.example.autoquest.data.helper

import androidx.room.ColumnInfo
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.data.database.entity.QuestTaskEntity
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestTask

fun QuestTask.toQuestTaskEntity() = QuestTaskEntity(
    questsId = questsId,
    textTask = textTask,
    answerTask = answerTask,
    latitude = latitude,
    longitude = longitude,
    questTaskImg = questTaskImg,
)

fun QuestTaskEntity.toQuestTask() = QuestTask(
    questsId = questsId,
    textTask = textTask,
    answerTask = answerTask,
    latitude = latitude,
    longitude = longitude,
    questTaskImg = questTaskImg
)

fun QuestItem.toQuestItemEntity() = QuestItemEntity(
    questsId = questsId,
    itemBackgroundImg = itemBackgroundImg,
    rating = rating,
    questDescription = questDescription,
    dataQuest = dataQuest,
    nameQuest = nameQuest,
    timeQuest = timeQuest,
    placeStartQuest = placeStartQuest,
    imgDetailsQuest = imgDetailsQuest,
    accessCode = accessCode
)

fun QuestItemEntity.toQuestItem() = QuestItem(
    questsId = questsId,
    itemBackgroundImg = itemBackgroundImg,
    rating = rating,
    questDescription = questDescription,
    dataQuest = dataQuest,
    nameQuest = nameQuest,
    timeQuest = timeQuest,
    placeStartQuest = placeStartQuest,
    imgDetailsQuest = imgDetailsQuest,
    accessCode = accessCode
)