package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.FetchUsersResultRepository

class FetchUsersResultUseCase(
    private val fetchUsersResultRepository: FetchUsersResultRepository
) {

    fun execute(questId: Int) = fetchUsersResultRepository.fetchUsersResult(questId)
}