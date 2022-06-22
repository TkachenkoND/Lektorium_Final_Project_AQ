package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.AddPointsToUserRepository

class AddPointsToUserRepositoryImpl(
    private val questDataSource: QuestDataSource
): AddPointsToUserRepository {
    override fun addPointsToUser(userId: String, userPoint: Float, questId: Int) {
        questDataSource.addPointsToUser(userId,userPoint,questId)
    }
}