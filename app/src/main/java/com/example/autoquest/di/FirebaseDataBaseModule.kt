package com.example.autoquest.di

import com.example.autoquest.data.fireBase.repository.QuestItemFirebase
import com.example.autoquest.data.fireBase.repository.QuestTaskFirebase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val fireBaseDataBase = module {

    factory { QuestItemFirebase(get()) }
    factory { QuestTaskFirebase(get()) }

    single { Firebase.firestore }
}