package com.example.autoquest.di

import com.example.autoquest.data.database.repository_impl.FetchAllQuestItemFromDbRepositoryImpl
import com.example.autoquest.data.database.repository_impl.FetchDataQuestItemFromDbRepositoryImpl
import com.example.autoquest.data.database.repository_impl.InsertQuestItemInDbRepositoryImpl
import com.example.autoquest.data.database.repository_impl.UpdateQuestItemInDbRepositoryImpl
import com.example.autoquest.data.fireBase.repository_impl.FetchFavoriteQuestItemFromFbRepositoryImpl
import com.example.autoquest.data.fireBase.repository_impl.FetchQuestItemListFromFbRepositoryImpl
import com.example.autoquest.data.fireBase.repository_impl.FetchQuestTaskListFromFbRepositoryImpl
import com.example.autoquest.data.fireBase.repository_impl.UpdateQuestIsFavoriteInFbRepositoryImpl
import com.example.autoquest.data.google_signIn.CheckUserRegisterStatusRepositoryImpl
import com.example.autoquest.data.locate.repository_impl.LocateRepositoryImpl
import com.example.autoquest.domain.repository.database_repository.FetchAllQuestItemFromDbRepository
import com.example.autoquest.domain.repository.database_repository.FetchDataQuestItemFromDbRepository
import com.example.autoquest.domain.repository.database_repository.InsertQuestItemInDbRepository
import com.example.autoquest.domain.repository.database_repository.UpdateQuestItemInDbRepository
import com.example.autoquest.domain.repository.fireBase_repository.FetchFavoriteQuestItemFromFbRepository
import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestItemListFromFbRepository
import com.example.autoquest.domain.repository.fireBase_repository.FetchQuestTaskListFromFbRepository
import com.example.autoquest.domain.repository.fireBase_repository.UpdateQuestIsFavoriteInFbRepository
import com.example.autoquest.domain.repository.google_signIn.CheckUserRegisterStatusRepository
import com.example.autoquest.domain.repository.locate_repository.LocateRepository
import org.koin.dsl.module

val dataModule = module {
    single<FetchAllQuestItemFromDbRepository> { FetchAllQuestItemFromDbRepositoryImpl(get()) }
    single<FetchDataQuestItemFromDbRepository> { FetchDataQuestItemFromDbRepositoryImpl(get()) }
    single<InsertQuestItemInDbRepository> { InsertQuestItemInDbRepositoryImpl(get()) }
    single<UpdateQuestItemInDbRepository> { UpdateQuestItemInDbRepositoryImpl(get()) }

    single<FetchFavoriteQuestItemFromFbRepository> { FetchFavoriteQuestItemFromFbRepositoryImpl(get()) }
    single<FetchQuestTaskListFromFbRepository> { FetchQuestTaskListFromFbRepositoryImpl(get()) }
    single<FetchQuestItemListFromFbRepository> { FetchQuestItemListFromFbRepositoryImpl(get()) }
    single<UpdateQuestIsFavoriteInFbRepository> { UpdateQuestIsFavoriteInFbRepositoryImpl(get()) }

    single<CheckUserRegisterStatusRepository> { CheckUserRegisterStatusRepositoryImpl(get()) }
    single<LocateRepository> { LocateRepositoryImpl(get()) }
}