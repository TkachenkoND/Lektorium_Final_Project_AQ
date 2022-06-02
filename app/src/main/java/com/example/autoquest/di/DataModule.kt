package com.example.autoquest.di

import com.example.autoquest.data.fireBase.repository_impl.QuestInFbRepositoryImpl
import com.example.autoquest.data.shared_preferences.WorkWithSharedPref
import com.example.autoquest.domain.repository.UpdateQuestIsFavoriteInFbRepository
import com.example.autoquest.domain.repository.FetchQuestItemListRepository
import com.example.autoquest.domain.repository.FetchQuestTaskListRepository
import org.koin.dsl.module

val dataModule = module {
    single<FetchQuestItemListRepository> { QuestInFbRepositoryImpl(get()) }
    single<FetchQuestTaskListRepository> { QuestInFbRepositoryImpl(get()) }
    single<UpdateQuestIsFavoriteInFbRepository> { QuestInFbRepositoryImpl(get()) }

    single { WorkWithSharedPref(get()) }
}