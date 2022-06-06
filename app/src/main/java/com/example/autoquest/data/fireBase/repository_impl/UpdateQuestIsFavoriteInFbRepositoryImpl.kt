package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.UpdateQuestIsFavoriteInFbRepository

class UpdateQuestIsFavoriteInFbRepositoryImpl(
    private val questDataSource: QuestDataSource
) : UpdateQuestIsFavoriteInFbRepository {
    override fun updateQuestIsFavoriteInFb(isFavorite:Boolean) {
        questDataSource.updateQuestIsFavorite(isFavorite)
    }

}