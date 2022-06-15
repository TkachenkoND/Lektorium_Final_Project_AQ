package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.RemoveQuestFromFavouritesRepository

class RemoveQuestFromFavouritesRepositoryImpl(
    private val questDataSource: QuestDataSource
) : RemoveQuestFromFavouritesRepository {
    override suspend fun removeQuestFromFavourites(questId: String, userId: String) {
        questDataSource.removeQuestFromFavourites(questId, userId)
    }
}