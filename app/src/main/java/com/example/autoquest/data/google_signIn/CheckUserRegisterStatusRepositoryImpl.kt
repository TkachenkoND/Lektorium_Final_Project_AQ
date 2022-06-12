package com.example.autoquest.data.google_signIn

import com.example.autoquest.domain.repository.google_signIn.CheckUserRegisterStatusRepository
import com.google.firebase.auth.FirebaseAuth

class CheckUserRegisterStatusRepositoryImpl(
    private val auth: FirebaseAuth
) : CheckUserRegisterStatusRepository {

    override fun checkUserRegisterStatus() = auth.currentUser != null
}