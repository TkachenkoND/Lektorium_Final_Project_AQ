package com.example.autoquest.di

import com.example.autoquest.data.repository_impl.QuestsDataRepositoryImpl
import com.example.autoquest.data.repository_impl.QuestsTasksRepositoryImpl
import com.example.autoquest.data.shared_preferences.WorkWithSharedPref
import com.example.autoquest.domain.repository.QuestsDataRepository
import com.example.autoquest.domain.repository.QuestsTasksRepository
import org.koin.dsl.module

val dataModule = module {
    single<QuestsDataRepository> { QuestsDataRepositoryImpl(get()) }
    single<QuestsTasksRepository> { QuestsTasksRepositoryImpl(get()) }

    single { WorkWithSharedPref(get()) }
}