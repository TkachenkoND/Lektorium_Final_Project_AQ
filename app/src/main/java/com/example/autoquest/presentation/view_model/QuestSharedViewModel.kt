package com.example.autoquest.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.usecases.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuestSharedViewModel(
    private val updateQuestIsFavoriteInFbUseCase: UpdateQuestIsFavoriteInFbUseCase,
    private val fetchQuestTaskListFromFbUseCase: FetchQuestTaskListFromFbUseCase,
    private val fetchQuestItemListFromFbUseCase: FetchQuestItemListFromFbUseCase,
    private val fetchAllQuestItemFromDbUseCase: FetchAllQuestItemFromDbUseCase,
    private val fetchDataQuestItemFromDbUseCase: FetchDataQuestItemFromDbUseCase,
    private val insertQuestItemInDbUseCase: InsertQuestItemInDbUseCase,
    private val updateQuestItemInDbUseCase: UpdateQuestItemInDbUseCase,
    private val fetchFavoriteQuestItemFromFbUseCase: FetchFavoriteQuestItemFromFbUseCase
) : ViewModel() {

    private val _questId = MutableStateFlow(0)
    val questId: MutableStateFlow<Int> = _questId

    private val _onlyFavoriteQuests = MutableStateFlow(QuestsItemList(emptyList()))
    val onlyFavoriteQuests: StateFlow<QuestsItemList> = _onlyFavoriteQuests

    private val _questItemList = MutableStateFlow(QuestsItemList(emptyList()))
    val questItemList: StateFlow<QuestsItemList> = _questItemList

    fun setQuestId(questId: Int) {
        _questId.value = questId
    }

    fun fetchQuestItemListFromFbVm() {
        viewModelScope.launch {
            fetchQuestItemListFromFbUseCase.execute().collect { questItemList ->
                _questItemList.value = questItemList
            }
        }
    }

    fun fetchFavoriteQuestItemFromFbVm() {
        viewModelScope.launch {
            fetchFavoriteQuestItemFromFbUseCase.execute().collect { questItemList ->
                _onlyFavoriteQuests.value = questItemList
            }
        }
    }

    fun updateQuestIsFavoriteInFb(isFavorite: Boolean) {
        updateQuestIsFavoriteInFbUseCase.execute(isFavorite)
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