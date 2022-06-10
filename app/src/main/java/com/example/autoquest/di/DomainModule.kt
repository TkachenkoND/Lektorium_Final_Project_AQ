package com.example.autoquest.di

import com.example.autoquest.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { FetchAllQuestItemFromDbUseCase(get()) }
    factory { FetchDataQuestItemFromDbUseCase(get()) }
    factory { FetchFavoriteQuestItemFromFbUseCase(get()) }
    factory { FetchQuestTaskListFromFbUseCase(get()) }
    factory { FetchQuestItemListFromFbUseCase(get()) }
    factory { InsertQuestItemInDbUseCase(get()) }
    factory { UpdateQuestIsFavoriteInFbUseCase(get()) }
    factory { UpdateQuestItemInDbUseCase(get()) }
    factory { LocateUseCase(get()) }
}