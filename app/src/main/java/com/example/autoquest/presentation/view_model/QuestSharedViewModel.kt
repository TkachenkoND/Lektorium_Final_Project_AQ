package com.example.autoquest.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.usecases.*

class QuestSharedViewModel(
    private val updateQuestIsFavoriteInFbUseCase: UpdateQuestIsFavoriteInFbUseCase,
    private val fetchQuestTaskListFromFbUseCase: FetchQuestTaskListFromFbUseCase,
    private val fetchQuestItemListFromFbUseCase: FetchQuestItemListFromFbUseCase,
    private val fetchAllQuestItemFromDbUseCase: FetchAllQuestItemFromDbUseCase,
    private val fetchDataQuestItemFromDbUseCase: FetchDataQuestItemFromDbUseCase,
    private val insertQuestItemInDbUseCase: InsertQuestItemInDbUseCase,
    private val updateQuestItemInDbUseCase: UpdateQuestItemInDbUseCase
) : ViewModel() {

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> = _isRegistered

    private val _questId = MutableLiveData<Int>()
    val questId: LiveData<Int> = _questId

    fun setQuestId(questId: Int) {
        _questId.value = questId
    }

    val questItemList = fetchQuestItemListFromFbUseCase.execute()

//    val questTaskList = fetchQuestTaskListFromFbUseCase.execute()

    fun updateQuestIsFavoriteInFb(questId: Int) {
        updateQuestIsFavoriteInFbUseCase.execute(questId)
    }

    //DataBase
    suspend fun fetchAllQuestItemFromDbVm() = fetchAllQuestItemFromDbUseCase.execute()

    suspend fun fetchDataQuestItemFromDbVm(questId: Int) =
        fetchDataQuestItemFromDbUseCase.execute(questId)

    suspend fun insertQuestItemInDbVm(questItem: QuestItemEntity) {
        insertQuestItemInDbUseCase.execute(questItem)
    }

    fun updateQuestItemInDbVm(questItem: QuestItemEntity) {
        updateQuestItemInDbUseCase.execute(questItem)
    }
}