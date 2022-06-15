package com.example.autoquest.di

import com.example.autoquest.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { FetchAllItemFromDbUseCase(get()) }
    factory { FetchDataItemFromDbUseCase(get()) }
    factory { FetchUserFavouriteQuestsUseCase(get()) }
    factory { FetchTaskListFromFbUseCase(get()) }
    factory { FetchItemListFromFbUseCase(get()) }
    factory { InsertItemInDbUseCase(get()) }
    factory { AddQuestToFavouritesUseCase(get()) }
    factory { LocateUseCase(get()) }
    factory { SaveUserInFireBaseUseCase(get()) }
    factory { RemoveQuestFromFavouritesUseCase(get()) }
}