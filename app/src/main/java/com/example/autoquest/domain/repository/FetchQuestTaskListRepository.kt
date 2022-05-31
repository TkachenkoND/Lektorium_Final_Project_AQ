package com.example.autoquest.domain.repository

import com.example.autoquest.domain.models.QuestsTasksList
import kotlinx.coroutines.flow.Flow

interface FetchQuestTaskListRepository {
    suspend fun fetchQuestTaskList(): Flow<QuestsTasksList>
}