package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.AddPointsToUserRepository

class AddPointsToUserUseCase(
    private val addPointsToUserRepository: AddPointsToUserRepository
) {
    fun execute(userId: String, userPoint: Float, questId: Int) {
        addPointsToUserRepository.addPointsToUser(userId, userPoint, questId)
    }
}