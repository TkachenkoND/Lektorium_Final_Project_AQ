package com.example.autoquest.di

import com.example.autoquest.presentation.view_model.DetailsQuestItemFragmentViewModel
import com.example.autoquest.presentation.view_model.ListOfQuestsViewModel
import com.example.autoquest.presentation.view_model.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailsQuestItemFragmentViewModel(get(), get(), get(), get(), get()) }
    viewModel { ListOfQuestsViewModel(get(), get(), get(), get()) }
    viewModel { SharedViewModel(get()) }

}