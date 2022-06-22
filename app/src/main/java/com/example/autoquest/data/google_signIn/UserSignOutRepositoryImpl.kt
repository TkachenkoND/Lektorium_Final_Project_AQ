package com.example.autoquest.data.google_signIn

import com.example.autoquest.domain.repository.google_sign.UserSignOutRepository
import com.google.firebase.auth.FirebaseAuth

class UserSignOutRepositoryImpl(
    private val auth: FirebaseAuth
) : UserSignOutRepository {

    override fun userSignOut() {
        auth.signOut()
    }

}