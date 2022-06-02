package com.example.autoquest.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.data.database.dao.QuestTaskDao
import com.example.autoquest.data.helper.toQuestItem
import com.example.autoquest.data.helper.toQuestTaskEntity
import com.example.autoquest.data.shared_preferences.WorkWithSharedPref
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.usecase.UpdateQuestIsFavoriteInFbUseCase
import com.example.autoquest.domain.usecase.FetchQuestItemListFromFbUseCase
import com.example.autoquest.domain.usecase.FetchQuestTaskListFromFbUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class QuestSharedViewModel(
    private val updateQuestIsFavoriteInFbUseCase: UpdateQuestIsFavoriteInFbUseCase,
    private val fetchQuestTaskListFromFbUseCase: FetchQuestTaskListFromFbUseCase,
    private val fetchQuestItemListFromFbUseCase: FetchQuestItemListFromFbUseCase,
    private val workWithSharedPref: WorkWithSharedPref,
    private val questTaskDao: QuestTaskDao,
    private val questItemDao: QuestItemDao,
) : ViewModel() {

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> = _isRegistered

    private val _questId = MutableLiveData<Int>()
    val questId: LiveData<Int> = _questId

    private val _isClickedBtnDialog = MutableLiveData<Boolean>()
    val isClickedBtnDialog: LiveData<Boolean> = _isClickedBtnDialog

    fun setQuestId(questId: Int) {
        _questId.value = questId
    }

    val questItemList = fetchQuestItemListFromFbUseCase.execute()
    var questTaskList = fetchQuestTaskListFromFbUseCase.execute()

    fun addQuestInFavorite(questId: Int) {
        updateQuestIsFavoriteInFbUseCase.execute(questId)
    }

    //Click Btn
    fun clickBtnDialog(isClicked: Boolean) {
        if (isClicked)
            _isClickedBtnDialog.postValue(true)
        else
            _isClickedBtnDialog.postValue(false)
    }

    //DataBase
    private fun insertQuestTaskInDataBaseVm() {
        viewModelScope.launch {
            try {
                questTaskList.collect { taskList ->
                    taskList.questTaskList.forEach {
                        questTaskDao.insertQuestTaskInDataBase(it.toQuestTaskEntity())
                    }
                }
            } catch (e: Exception) {
                Log.d("InsertException", e.toString())
            }
        }
    }

    fun getQuestTaskFromDataBaseVm(questsId: Int) {
        viewModelScope.launch {
            try {
                questTaskList = questTaskDao.getDataQuestTask(questsId)
            } catch (e: Exception) {
                Log.d("GetTaskException", e.toString())
            }
        }
    }

    fun getQuestItemFromDataBaseVm(questsId: Int) {
        viewModelScope.launch {
            try {
                questItemList = questItemDao.fetchDataQuestItemFromDb(questsId)
            } catch (e: Exception) {
                Log.d("GetItemException", e.toString())
            }
        }
    }

    private fun insertQuestItemInDataBaseVm() {
        viewModelScope.launch {
            try {
                _listQuests.value!!.questList.forEach {
                    questItemDao.insertQuestItemInDb(it.toQuestItemEntity())
                }
            } catch (e: Exception) {
                Log.d("InsertException", e.toString())
            }
        }
    }

    fun getAllQuestItemFromDataBaseVm() {
        viewModelScope.launch {
            try {
                val data = questItemDao.fetchAllQuestItemFromDb()
                val listToQuestItem = mutableListOf<QuestItem>()

                data.forEach {
                    listToQuestItem.add(it.toQuestItem())
                }

                _listQuests.postValue(QuestsItemList(listToQuestItem))
                _isGetAllQuestItem.postValue(true)
            } catch (e: Exception) {
                Log.d("GetAllException", e.toString())
                _isGetAllQuestItem.postValue(false)
            }
        }
    }

    fun checkAccessCode(editCode: String, accessCode: String) {
        _isCorrectCode.value = editCode == accessCode
    }

}