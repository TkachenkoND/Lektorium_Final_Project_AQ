package com.example.autoquest.di

import com.example.autoquest.data.shared_preferences.WorkWithSharedPref
import org.koin.dsl.module

val dataModule = module {
    single<QuestsDataRepository> { QuestsDataRepositoryImpl(get()) }
    single<QuestsTasksRepository> { QuestsTasksRepositoryImpl(get()) }

    single { WorkWithSharedPref(get()) }
}