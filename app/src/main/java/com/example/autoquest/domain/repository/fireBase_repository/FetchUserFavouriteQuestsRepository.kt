package com.example.autoquest.domain.repository.fireBase_repository

import kotlinx.coroutines.flow.Flow

interface FetchUserFavouriteQuestsRepository {
    fun getUserFavouriteQuests(userId: String): Flow<MutableList<Long>>
}