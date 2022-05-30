package com.example.autoquest.di

import com.example.autoquest.domain.usecase.LoadQuestsDataUseCase
import com.example.autoquest.domain.usecase.LoadQuestsTasksUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { LoadQuestsDataUseCase(get()) }
    factory { LoadQuestsTasksUseCase(get()) }
}