package com.example.autoquest.di

import androidx.room.Room
import com.example.autoquest.data.database.QuestsDataBase
import org.koin.dsl.module

private const val DB_NAME = "Quests.db"

val dbModule = module {

    single {
        Room.databaseBuilder(get(), QuestsDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<QuestsDataBase>().getQuestItemDao() }
    factory { get<QuestsDataBase>().getQuestTaskDao() }
}