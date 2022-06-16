package com.example.autoquest.domain.repository

import com.example.autoquest.domain.models.User

interface CheckUserRegisterStatusAndGetIdRepository {
    fun checkUserRegisterStatusAndGetId(): User?
}