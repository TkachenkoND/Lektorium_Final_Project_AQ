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
import kotlinx.coroutines.launch

class DetailsQuestItemFragmentViewModel(
    private val fetchAllItemFromDbUseCase: FetchAllItemFromDbUseCase,
    private val fetchDataItemFromDbUseCase: FetchDataItemFromDbUseCase,
    private val insertItemInDbUseCase: InsertItemInDbUseCase,
    private val updateItemInDbUseCase: UpdateItemInDbUseCase,
    private val locateUseCase: LocateUseCase
) : ViewModel() {

    var getQuestTaskListSize: Int? = null

    private val _questTaskId = MutableStateFlow(0)
    val questTaskId: MutableStateFlow<Int> = _questTaskId

    private val _questTaskList = MutableStateFlow(QuestsTasksList(emptyList()))
    val questTaskList: StateFlow<QuestsTasksList> = _questTaskList

    fun setQuestTaskId(questTaskId: Int) {
        _questTaskId.value = questTaskId
    }

    fun fetchQuestTaskFromFbVm(id: Int): QuestTask? {
        getQuestTaskListSize = _questTaskList.value.questTaskList.size

        _questTaskList.value.questTaskList.forEach { questTask ->
            if (questTask.questsId == id)
                return questTask
        }
        return null
    }

    //DataBase
    suspend fun fetchAllQuestItemFromDbVm() = fetchAllItemFromDbUseCase.execute()

    suspend fun fetchDataQuestItemFromDbVm(questId: Int) =
        fetchDataItemFromDbUseCase.execute(questId)

    suspend fun insertQuestItemInDbVm(questItem: QuestItemEntity) {
        insertItemInDbUseCase.execute(questItem)
    }

    fun updateQuestItemInDbVm(questItem: QuestItemEntity) {
        updateItemInDbUseCase.execute(questItem)
    }

    //Locate
    fun getLocate(latitude: Double, longitude: Double, mapFragment: SupportMapFragment) {
        locateUseCase.execute(latitude, longitude, mapFragment)
    }

}