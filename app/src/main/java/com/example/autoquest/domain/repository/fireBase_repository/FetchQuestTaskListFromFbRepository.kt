package com.example.autoquest.domain.repository.fireBase_repository

import com.example.autoquest.domain.models.QuestsTasksList
import kotlinx.coroutines.flow.Flow

interface FetchQuestTaskListFromFbRepository {
    fun fetchQuestTaskList(): Flow<QuestsTasksList>
}