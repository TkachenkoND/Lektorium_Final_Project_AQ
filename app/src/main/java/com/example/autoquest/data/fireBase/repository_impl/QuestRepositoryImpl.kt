package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import com.example.autoquest.domain.repository.AddQuestInFavoriteRepository
import com.example.autoquest.domain.repository.FetchQuestItemListRepository
import com.example.autoquest.domain.repository.FetchQuestTaskListRepository
import kotlinx.coroutines.flow.Flow

class QuestRepositoryImpl(
    private val questDataSource: QuestDataSource
) : FetchQuestTaskListRepository, FetchQuestItemListRepository, AddQuestInFavoriteRepository {

    override suspend fun addQuestInFavorite(questId: Int) {
        questDataSource.addQuestInFavorite(questId)
    }

    override suspend fun fetchQuestItemList() = questDataSource.fetchQuestItemList()

    override suspend fun fetchQuestTaskList() = questDataSource.fetchQuestTaskList()

}