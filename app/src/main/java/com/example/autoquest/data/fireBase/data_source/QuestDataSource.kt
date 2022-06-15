package com.example.autoquest.data.fireBase.data_source

import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import kotlinx.coroutines.flow.Flow

interface QuestDataSource {

    fun fetchQuestItemList(): Flow<QuestsItemList>
    fun fetchQuestTaskList(): Flow<QuestsTasksList>
    fun addQuestToFavourites(userId: Int, questId: Int)
    fun saveUserInFb(userId: Int, userName: String, userImg: String)
    fun getUserFavouriteQuests(userId: Int):  Flow<MutableList<String>>
    suspend fun removeQuestFromFavourites(questId: String, userId: String)
}