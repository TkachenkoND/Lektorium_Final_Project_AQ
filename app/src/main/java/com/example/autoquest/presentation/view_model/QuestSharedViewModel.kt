package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.data.database.entity.QuestItemEntity
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestTask
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import com.example.autoquest.domain.usecases.*
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class QuestSharedViewModel(
    private val updateQuestIsFavoriteInFbUseCase: UpdateQuestIsFavoriteInFbUseCase,
    private val fetchQuestTaskListFromFbUseCase: FetchQuestTaskListFromFbUseCase,
    private val fetchQuestItemListFromFbUseCase: FetchQuestItemListFromFbUseCase,
    private val fetchAllQuestItemFromDbUseCase: FetchAllQuestItemFromDbUseCase,
    private val fetchDataQuestItemFromDbUseCase: FetchDataQuestItemFromDbUseCase,
    private val insertQuestItemInDbUseCase: InsertQuestItemInDbUseCase,
    private val updateQuestItemInDbUseCase: UpdateQuestItemInDbUseCase,
    private val fetchFavoriteQuestItemFromFbUseCase: FetchFavoriteQuestItemFromFbUseCase,
    private val locateUseCase: LocateUseCase
) : ViewModel() {

    var getQuestTaskListSize: Int? = null

    private val _questId = MutableStateFlow(0)
    val questId: MutableStateFlow<Int> = _questId

    private val _questTaskId = MutableStateFlow(0)
    val questTaskId: MutableStateFlow<Int> = _questTaskId

    private val _onlyFavoriteQuests = MutableStateFlow(QuestsItemList(emptyList()))
    val onlyFavoriteQuests: StateFlow<QuestsItemList> = _onlyFavoriteQuests

    private val _questItemList = MutableStateFlow(QuestsItemList(emptyList()))
    val questItemList: StateFlow<QuestsItemList> = _questItemList

    private val _questItem = MutableStateFlow(null)
    val questItem: MutableStateFlow<Nothing?> = _questItem

    private val _questTask = MutableStateFlow(null)
    val questTask: MutableStateFlow<Nothing?> = _questTask

    private val _questTaskList = MutableStateFlow(QuestsTasksList(emptyList()))
    val questTaskList: StateFlow<QuestsTasksList> = _questTaskList

    fun setQuestTaskId(questTaskId: Int) {
        _questTaskId.value = questTaskId
    }

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

    fun fetchQuestTaskListFromFbVm() {
        viewModelScope.launch {
            fetchQuestTaskListFromFbUseCase.execute().collect { questTaskList ->
                _questTaskList.value = questTaskList
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

    fun fetchQuestItemFromFbVm(id: Int): QuestItem? {
        _questItemList.value.questItemList.forEach { questItem ->
            if (questItem.questsId == id)
                return questItem
        }
        return null
    }

    //Locate
    fun getLocate(latitude: Double, longitude: Double, mapFragment: SupportMapFragment) =
        locateUseCase.execute(latitude, longitude, mapFragment)

    fun fetchQuestTaskFromFbVm(id: Int): QuestTask? {
        getQuestTaskListSize = _questTaskList.value.questTaskList.size

        _questTaskList.value.questTaskList.forEach { questTask ->
            if (questTask.questsId == id)
                return questTask
        }
        return null
    }

}