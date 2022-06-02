package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestItemListFromFbRepository
import kotlinx.coroutines.flow.Flow

class FetchQuestItemListFromFbRepositoryImpl(
    private val questDataSource: QuestDataSource
) : FetchQuestItemListFromFbRepository {
    override fun fetchQuestItemList() = questDataSource.fetchQuestItemList()
}