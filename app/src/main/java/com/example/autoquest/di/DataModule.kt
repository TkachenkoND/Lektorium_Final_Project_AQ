package com.example.autoquest.di

import com.example.autoquest.data.shared_preferences.WorkWithSharedPref
import com.example.autoquest.domain.repository.fireBase_repository.UpdateQuestIsFavoriteInFbRepository
import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestItemListFromFbRepository
import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestTaskListFromFbRepository
import org.koin.dsl.module

val dataModule = module {
    single<FetchQuestItemListFromFbRepository> { QuestInFbFromFbFromFbRepositoryImpl(get()) }
    single<FetchQuestTaskListFromFbRepository> { QuestInFbFromFbFromFbRepositoryImpl(get()) }
    single<UpdateQuestIsFavoriteInFbRepository> { QuestInFbFromFbFromFbRepositoryImpl(get()) }

    single { WorkWithSharedPref(get()) }
}