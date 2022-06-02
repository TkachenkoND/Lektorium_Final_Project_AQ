package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestTaskListFromFbRepository

class FetchQuestTaskListFromFbRepositoryImpl(
    private val questDataSource: QuestDataSource
) : FetchQuestTaskListFromFbRepository {
    override fun fetchQuestTaskList() = questDataSource.fetchQuestTaskList()
}