package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.fireBase_repository.SaveUserInFireBaseRepository

class SaveUserInFireBaseUseCase(
    private val saveUserInFireBaseRepository: SaveUserInFireBaseRepository
) {
    fun execute(userId: String, userName: String, userImg: String) {
        saveUserInFireBaseRepository.saveUserInFb(userId,userName,userImg)
    }
}