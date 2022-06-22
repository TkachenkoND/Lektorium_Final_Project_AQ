package com.example.autoquest.data.helper

import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.models.QuestItem

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