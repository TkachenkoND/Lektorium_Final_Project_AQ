package com.example.autoquest.data.google_signIn

import com.example.autoquest.domain.models.User
import com.example.autoquest.domain.repository.google_sign.CheckUserRegisterStatusAndGetIdRepository
import com.google.firebase.auth.FirebaseAuth

class CheckUserRegisterStatusAndGetIdRepositoryImpl(
    private val auth: FirebaseAuth
) : CheckUserRegisterStatusAndGetIdRepository {
    override fun checkUserRegisterStatusAndGetId(): User? {
        val user = auth.currentUser
        return if (user != null)
            User(user.uid, user.photoUrl.toString(), user.displayName!!)
        else
            null
    }

}