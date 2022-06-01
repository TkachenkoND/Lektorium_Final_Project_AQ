package com.example.autoquest.di

import com.example.autoquest.data.fireBase.repository_impl.QuestRepositoryImpl
import com.example.autoquest.data.shared_preferences.WorkWithSharedPref
import com.example.autoquest.domain.repository.AddQuestInFavoriteRepository
import com.example.autoquest.domain.repository.FetchQuestItemListRepository
import com.example.autoquest.domain.repository.FetchQuestTaskListRepository
import org.koin.dsl.module

val dataModule = module {
    single<FetchQuestItemListRepository> { QuestRepositoryImpl(get()) }
    single<FetchQuestTaskListRepository> { QuestRepositoryImpl(get()) }
    single<AddQuestInFavoriteRepository> { QuestRepositoryImpl(get()) }

    single { WorkWithSharedPref(get()) }
}