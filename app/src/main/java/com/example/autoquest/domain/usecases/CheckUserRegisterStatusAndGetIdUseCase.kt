package com.example.autoquest.domain.usecases

import com.example.autoquest.domain.repository.CheckUserRegisterStatusAndGetIdRepository

class CheckUserRegisterStatusAndGetIdUseCase(
    private val checkUserRegisterStatusAndGetIdRepository: CheckUserRegisterStatusAndGetIdRepository
) {
    fun execute() = checkUserRegisterStatusAndGetIdRepository.checkUserRegisterStatusAndGetId()
}