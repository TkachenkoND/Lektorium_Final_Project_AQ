package com.example.autoquest.data.fireBase.repository_impl

import com.example.autoquest.data.fireBase.data_source.QuestDataSource
import com.example.autoquest.domain.repository.fireBase_repository.SaveUserInFireBaseRepository

class SaveUserInFireBaseRepositoryImpl(
    private val questDataSource: QuestDataSource

) : SaveUserInFireBaseRepository {
    override fun saveUserInFb(userId: String, userName: String, userImg: String) {
        questDataSource.saveUserInFb(userId,userName,userImg)
    }
}