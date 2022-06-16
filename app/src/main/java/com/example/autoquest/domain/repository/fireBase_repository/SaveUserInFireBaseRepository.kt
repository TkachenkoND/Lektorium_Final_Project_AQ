package com.example.autoquest.domain.repository.fireBase_repository

interface SaveUserInFireBaseRepository {
    fun saveUserInFb(userId: String, userName: String, userImg: String)
}