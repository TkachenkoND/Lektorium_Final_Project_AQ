package com.example.autoquest.domain.repository

import com.example.autoquest.domain.models.QuestsItemList
import kotlinx.coroutines.flow.Flow

interface FetchQuestItemListRepository {
    suspend fun fetchQuestItemList(): Flow<QuestsItemList>
}