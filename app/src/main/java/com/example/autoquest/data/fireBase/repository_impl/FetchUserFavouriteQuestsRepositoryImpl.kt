package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.FetchUserFavouriteQuestsRepository

class FetchUserFavouriteQuestsRepositoryImpl(
    private val questDataSource: QuestDataSource
) : FetchUserFavouriteQuestsRepository {

    override fun getUserFavouriteQuests(userId: String) = questDataSource.getUserFavouriteQuests(userId)
}