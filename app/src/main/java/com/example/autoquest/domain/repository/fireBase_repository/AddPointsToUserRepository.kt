package com.example.autoquest.domain.repository.fireBase_repository

interface AddPointsToUserRepository {
    fun addPointsToUser(userId: String, userPoint: Float, questId: Int)
}