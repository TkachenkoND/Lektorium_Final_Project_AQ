package com.example.autoquest.di

import com.example.autoquest.domain.usecase.AddQuestInFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { AddQuestInFavoriteUseCase(get()) }
    factory { LoadQuestsTasksUseCase(get()) }
}