package com.example.autoquest.di

import com.example.autoquest.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { FetchAllItemFromDbUseCase(get()) }
    factory { FetchDataItemFromDbUseCase(get()) }
    factory { FetchFavoriteItemFromFbUseCase(get()) }
    factory { FetchTaskListFromFbUseCase(get()) }
    factory { FetchItemListFromFbUseCase(get()) }
    factory { InsertItemInDbUseCase(get()) }
    factory { UpdateIsFavoriteInFbUseCase(get()) }
    factory { UpdateItemInDbUseCase(get()) }
    factory { LocateUseCase(get()) }
    factory { CheckUserRegisterStatusUseCase(get()) }
    factory { SaveUserInFireBaseUseCase(get()) }
}