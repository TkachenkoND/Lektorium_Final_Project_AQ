package com.example.autoquest.di

import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QuestSharedViewModel(get(),get(),get(),get(),get(),get(),get(),get()) }
}