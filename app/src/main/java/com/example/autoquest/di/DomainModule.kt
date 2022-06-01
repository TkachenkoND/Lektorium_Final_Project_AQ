package com.example.autoquest.di

import com.example.autoquest.domain.usecase.AddQuestInFavoriteUseCase
import com.example.autoquest.domain.usecase.FetchQuestItemListUseCase
import com.example.autoquest.domain.usecase.FetchQuestTaskListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { AddQuestInFavoriteUseCase(get()) }
    factory { FetchQuestItemListUseCase(get()) }
    factory { FetchQuestTaskListUseCase(get()) }
}