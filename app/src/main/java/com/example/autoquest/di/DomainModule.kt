package com.example.autoquest.di

import com.example.autoquest.domain.usecases.UpdateQuestIsFavoriteInFbUseCase
import com.example.autoquest.domain.usecases.FetchQuestItemListFromFbUseCase
import com.example.autoquest.domain.usecases.FetchQuestTaskListFromFbUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { UpdateQuestIsFavoriteInFbUseCase(get()) }
    factory { FetchQuestItemListFromFbUseCase(get()) }
    factory { FetchQuestTaskListFromFbUseCase(get()) }
}