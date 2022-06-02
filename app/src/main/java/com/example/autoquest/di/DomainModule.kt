package com.example.autoquest.di

import com.example.autoquest.domain.usecase.UpdateQuestIsFavoriteInFbUseCase
import com.example.autoquest.domain.usecase.FetchQuestItemListFromFbUseCase
import com.example.autoquest.domain.usecase.FetchQuestTaskListFromFbUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { UpdateQuestIsFavoriteInFbUseCase(get()) }
    factory { FetchQuestItemListFromFbUseCase(get()) }
    factory { FetchQuestTaskListFromFbUseCase(get()) }
}