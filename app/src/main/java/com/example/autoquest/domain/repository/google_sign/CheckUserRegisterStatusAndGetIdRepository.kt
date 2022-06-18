package com.example.autoquest.domain.repository.google_sign

import com.example.autoquest.domain.models.User

interface CheckUserRegisterStatusAndGetIdRepository {
    fun checkUserRegisterStatusAndGetId(): User?
}