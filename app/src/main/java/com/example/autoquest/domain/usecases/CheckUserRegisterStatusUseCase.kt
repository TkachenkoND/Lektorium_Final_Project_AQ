package com.example.autoquest.domain.usecases

import android.content.Intent
import com.example.autoquest.domain.repository.google_signIn.CheckUserRegisterStatusRepository

class CheckUserRegisterStatusUseCase(
    private val checkUserRegisterStatusRepository: CheckUserRegisterStatusRepository
) {
    fun execute() =  checkUserRegisterStatusRepository.checkUserRegisterStatus()

}