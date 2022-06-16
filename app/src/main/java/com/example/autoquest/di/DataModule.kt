package com.example.autoquest.di

import com.example.autoquest.data.database.repository_impl.FetchAllQuestItemFromDbRepositoryImpl
import com.example.autoquest.data.database.repository_impl.FetchDataQuestItemFromDbRepositoryImpl
import com.example.autoquest.data.database.repository_impl.InsertQuestItemInDbRepositoryImpl
import com.example.autoquest.data.database.repository_impl.UpdateQuestItemInDbRepositoryImpl
import com.example.autoquest.data.fireBase.repository_impl.*
import com.example.autoquest.data.google_signIn.CheckUserRegisterStatusAndGetIdRepositoryImpl
import com.example.autoquest.data.locate.repository_impl.LocateRepositoryImpl
import com.example.autoquest.domain.repository.CheckUserRegisterStatusAndGetIdRepository
import com.example.autoquest.domain.repository.fireBase_repository.RemoveQuestFromFavouritesRepository
import com.example.autoquest.domain.repository.database_repository.FetchAllQuestItemFromDbRepository
import com.example.autoquest.domain.repository.database_repository.FetchDataQuestItemFromDbRepository
import com.example.autoquest.domain.repository.database_repository.InsertQuestItemInDbRepository
import com.example.autoquest.domain.repository.database_repository.UpdateQuestItemInDbRepository
import com.example.autoquest.domain.repository.fireBase_repository.*
import com.example.autoquest.domain.repository.locate_repository.LocateRepository
import org.koin.dsl.module

val dataModule = module {
    single<FetchAllQuestItemFromDbRepository> { FetchAllQuestItemFromDbRepositoryImpl(get()) }
    single<FetchDataQuestItemFromDbRepository> { FetchDataQuestItemFromDbRepositoryImpl(get()) }
    single<InsertQuestItemInDbRepository> { InsertQuestItemInDbRepositoryImpl(get()) }
    single<UpdateQuestItemInDbRepository> { UpdateQuestItemInDbRepositoryImpl(get()) }
    single<FetchUserFavouriteQuestsRepository> { FetchUserFavouriteQuestsRepositoryImpl(get()) }
    single<FetchQuestTaskListFromFbRepository> { FetchQuestTaskListFromFbRepositoryImpl(get()) }
    single<FetchQuestItemListFromFbRepository> { FetchQuestItemListFromFbRepositoryImpl(get()) }
    single<AddQuestToFavouritesRepository> { AddQuestToFavouritesRepositoryImpl(get()) }
    single<RemoveQuestFromFavouritesRepository> { RemoveQuestFromFavouritesRepositoryImpl(get()) }
    single<LocateRepository> { LocateRepositoryImpl(get()) }
    single<SaveUserInFireBaseRepository> { SaveUserInFireBaseRepositoryImpl(get()) }
    single<CheckUserRegisterStatusAndGetIdRepository> {
        CheckUserRegisterStatusAndGetIdRepositoryImpl(get())
    }
}