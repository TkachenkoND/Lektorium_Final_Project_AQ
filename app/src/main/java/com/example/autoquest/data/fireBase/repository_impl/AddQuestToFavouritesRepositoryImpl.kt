package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.AddQuestToFavouritesRepository

class AddQuestToFavouritesRepositoryImpl(
    private val questDataSource: QuestDataSource

) : AddQuestToFavouritesRepository {
    override fun addQuestToFavourites(userId: String, questId: Int, changeCallback: (result: Boolean) -> Unit) {
        questDataSource.addQuestToFavourites(userId, questId, changeCallback)
    }
}