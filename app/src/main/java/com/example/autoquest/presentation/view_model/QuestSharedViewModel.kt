package com.example.autoquest.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.data.database.dao.QuestTaskDao
import com.example.autoquest.data.helper.toQuestItem
import com.example.autoquest.data.helper.toQuestTask
import com.example.autoquest.data.shared_preferences.WorkWithSharedPref
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestTask
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import com.example.autoquest.domain.usecase.AddQuestInFavoriteUseCase
import com.example.autoquest.domain.usecase.FetchQuestItemListUseCase
import com.example.autoquest.domain.usecase.FetchQuestTaskListUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class QuestSharedViewModel(
    private val addQuestInFavoriteUseCase: AddQuestInFavoriteUseCase,
    private val fetchQuestTaskListUseCase: FetchQuestTaskListUseCase,
    private val fetchQuestItemListUseCase: FetchQuestItemListUseCase,
    private val workWithSharedPref: WorkWithSharedPref,
    private val questTaskDao: QuestTaskDao,
    private val questItemDao: QuestItemDao,
) : ViewModel() {

    //Id
    fun setQuestId(questId: Int) {
        _questId.value = questId
    }

    val questItemList = fetchQuestItemListUseCase.execute()

    //Shared
    fun saveFlagUserIsAuthorizedInShared() {
        workWithSharedPref.saveFlagUserIsAuthorizedInShared()
    }

    fun fetchFlagUserIsAuthorizedFromShared() {
        if (workWithSharedPref.fetchFlagUserIsAuthorizedFromShared())
            _isRegistered.postValue(true)
        else
            _isRegistered.postValue(false)
    }

    fun saveQuestIdInShared(questId: Int) {
        workWithSharedPref.saveQuestIdInShared(questId)
    }

    fun fetchQuestIdFromShared() {
        workWithSharedPref.fetchQuestIdFromShared()
    }

    //Click Btn
    fun clickBtnDialog(isClicked: Boolean) {
        if (isClicked)
            _isClickedBtnDialog.postValue(true)
        else
            _isClickedBtnDialog.postValue(false)
    }

    //DataBase
    private suspend fun insertQuestTaskInDataBaseVm() {
        viewModelScope.launch {
            try {
                _listTasksQuests.value!!.questTaskList.forEach {
                    questTaskDao.insertQuestTaskInDataBase(it.toQuestTaskEntity())
                }
            } catch (e: Exception) {
                Log.d("InsertException", e.toString())
            }
        }
    }

    fun getAllQuestTaskFromDataBaseVm() {
        viewModelScope.launch {
            try {
                val data = questTaskDao.getAllQuestTaskFromDataBase()
                val listToQuestTask = mutableListOf<QuestTask>()

                data.forEach {
                    listToQuestTask.add(it.toQuestTask())
                }

                _listTasksQuests.postValue(QuestsTasksList(listToQuestTask))
                _isGetAllQuestTask.postValue(true)
            } catch (e: Exception) {
                Log.d("GetAllException", e.toString())
            }
        }
    }

    fun getQuestTaskFromDataBaseVm(questsId: Int) {
        viewModelScope.launch {
            try {
                _questTask.postValue(questTaskDao.getDataQuestTask(questsId).toQuestTask())
            } catch (e: Exception) {
                Log.d("GetTaskException", e.toString())
            }
        }
    }

    fun getQuestItemFromDataBaseVm(questsId: Int) {
        viewModelScope.launch {
            try {
                _questItem.postValue(questItemDao.getDataQuestItem(questsId).toQuestItem())
            } catch (e: Exception) {
                Log.d("GetItemException", e.toString())
            }
        }
    }

    private suspend fun insertQuestItemInDataBaseVm() {
        viewModelScope.launch {
            try {
                _listQuests.value!!.questList.forEach {
                    questItemDao.insertQuestItemInDataBase(it.toQuestItemEntity())
                }
            } catch (e: Exception) {
                Log.d("InsertException", e.toString())
            }
        }
    }

    fun getAllQuestItemFromDataBaseVm() {
        viewModelScope.launch {
            try {
                val data = questItemDao.getAllQuestItemFromDataBase()
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