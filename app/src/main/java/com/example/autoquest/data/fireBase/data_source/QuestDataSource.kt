package com.example.autoquest.data.fireBase.data_source

import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import com.example.autoquest.domain.models.User
import com.example.autoquest.domain.models.UserResultModel
import kotlinx.coroutines.flow.Flow

interface QuestDataSource {

    fun fetchQuestItemList(): Flow<QuestsItemList>
    fun fetchQuestTaskList(): Flow<QuestsTasksList>
    fun addQuestToFavourites(
        userId: String,
        questId: Int,
        changeCallback: (result: Boolean) -> Unit
    )

    fun saveUserInFb(userId: String, userName: String, userImg: String)
    fun getUserFavouriteQuests(userId: String): Flow<MutableList<Long>>
    fun addPointsToUser(userId: String, userPoint: Float, questId: Int)
    //fun fetchUsersResult(questId: Int): Flow<List<UserResultModel>>
    //fun fetchUsersByQuestId(questId: Int): Flow<MutableList<User>>
}