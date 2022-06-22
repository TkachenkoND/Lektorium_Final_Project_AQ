package com.example.autoquest.di

import com.example.autoquest.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    factory { FetchUserFavouriteQuestsUseCase(get()) }
    factory { FetchTaskListFromFbUseCase(get()) }
    factory { FetchItemListFromFbUseCase(get()) }
    factory { AddQuestToFavouritesUseCase(get()) }
    factory { LocateUseCase(get()) }
    factory { SaveUserInFireBaseUseCase(get()) }
    factory { CheckUserRegisterStatusAndGetIdUseCase(get()) }
    factory { UserSignOutUseCase(get()) }
    factory { AddPointsToUserUseCase(get()) }
}