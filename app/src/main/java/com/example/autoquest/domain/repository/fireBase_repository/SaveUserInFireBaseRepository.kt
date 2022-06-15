package com.example.autoquest.domain.repository.fireBase_repository

interface SaveUserInFireBaseRepository {
    fun saveUserInFb(userId: Int, userName: String, userImg: String)
}