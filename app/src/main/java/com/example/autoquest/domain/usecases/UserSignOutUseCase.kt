package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.google_sign.UserSignOutRepository

class UserSignOutUseCase(
    private val userSignOutRepository: UserSignOutRepository
) {
    fun execute() {
        userSignOutRepository.userSignOut()
    }
}