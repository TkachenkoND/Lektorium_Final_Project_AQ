package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.FetchFavoriteQuestItemFromFbRepository

class FetchFavoriteQuestItemFromFbRepositoryImpl(
    private val questDataSource: QuestDataSource
) : FetchFavoriteQuestItemFromFbRepository {
    override fun fetchFavoriteQuestItemFromFb() = questDataSource.fetchFavoriteQuestItem()
}