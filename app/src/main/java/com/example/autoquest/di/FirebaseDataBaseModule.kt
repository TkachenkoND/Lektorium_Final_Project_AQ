package com.example.autoquest.di

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.data.fireBase.data_source_impl.QuestDataSourceImpl
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val fireBaseDataBase = module {

    single<QuestDataSource> { QuestDataSourceImpl(get()) }

    single { Firebase.firestore }
}