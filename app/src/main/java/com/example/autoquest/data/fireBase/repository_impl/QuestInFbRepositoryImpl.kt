package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.UpdateQuestIsFavoriteInFbRepository
import com.example.autoquest.domain.repository.FetchQuestItemListRepository
import com.example.autoquest.domain.repository.FetchQuestTaskListRepository

class QuestInFbRepositoryImpl(
    private val questDataSource: QuestDataSource
) : FetchQuestTaskListRepository, FetchQuestItemListRepository, UpdateQuestIsFavoriteInFbRepository {

    override fun updateQuestIsFavoriteInFb(questId: Int) {
        questDataSource.addQuestInFavorite(questId)
    }

    override fun fetchQuestItemList() = questDataSource.fetchQuestItemList()

    override fun fetchQuestTaskList() = questDataSource.fetchQuestTaskList()

}