package com.example.autoquest.domain.repository.fireBase_repository

import com.example.autoquest.domain.models.UserResultModel
import kotlinx.coroutines.flow.Flow

interface FetchUsersResultRepository {
     fun fetchUsersResult(questId: Int): Flow<List<UserResultModel>>
}