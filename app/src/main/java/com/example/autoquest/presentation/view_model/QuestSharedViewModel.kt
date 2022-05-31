package com.example.autoquest.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.data.database.dao.QuestItemDao
import com.example.autoquest.data.database.dao.QuestTaskDao
import com.example.autoquest.data.helper.toQuestItem
import com.example.autoquest.data.helper.toQuestItemEntity
import com.example.autoquest.data.helper.toQuestTask
import com.example.autoquest.data.helper.toQuestTaskEntity
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

    private var _questId = MutableLiveData<Int>()
    val questId: LiveData<Int> = _questId

    private var _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> = _isRegistered

    private val _listQuests = MutableLiveData<QuestsItemList>()
    val listQuestsItem: LiveData<QuestsItemList> = _listQuests

    private var _isLoadingListQuests = MutableLiveData<Boolean>()
    val isLoadingListQuests: LiveData<Boolean> = _isLoadingListQuests

    private val _listTasksQuests = MutableLiveData<QuestsTasksList>()
    val listTasksQuests: LiveData<QuestsTasksList> = _listTasksQuests

    private val _questTask = MutableLiveData<QuestTask>()
    val questTask: LiveData<QuestTask> = _questTask

    private val _questItem = MutableLiveData<QuestItem>()
    val questItem: LiveData<QuestItem> = _questItem

    private var _isCorrectCode = MutableLiveData<Boolean>()
    val isCorrectCode: LiveData<Boolean> = _isCorrectCode

    private var _isLoadingListTasksQuests = MutableLiveData<Boolean>()
    val isLoadingListTasksQuests: LiveData<Boolean> = _isLoadingListTasksQuests

    private var _isClickedBtnDialog = MutableLiveData<Boolean>()
    val isClickedBtnDialog: LiveData<Boolean> = _isClickedBtnDialog

    private var _isGetAllQuestTask = MutableLiveData<Boolean>()
    val isGetAllQuestTask: LiveData<Boolean> = _isGetAllQuestTask

    private var _isGetAllQuestItem = MutableLiveData<Boolean>()
    val isGetAllQuestItem: LiveData<Boolean> = _isGetAllQuestItem

    //Id
    fun setQuestId(questId: Int) {
        _questId.value = questId
    }

    //Api
    fun loadQuestsDataVm() {
        viewModelScope.launch {
            try {
                _listQuests.postValue(addQuestInFavoriteUseCase.execute())
                loadQuestsTasksVm()
                insertQuestItemInDataBaseVm()
                insertQuestTaskInDataBaseVm()

                _isLoadingListQuests.postValue(true)
            } catch (e: Exception) {
                Log.d("LoadException", e.toString())
                _isLoadingListQuests.postValue(false)
            }
        }
    }

    private suspend fun loadQuestsTasksVm() {
        viewModelScope.launch {
            try {
                _listTasksQuests.postValue(loadQuestsTasksUseCase.execute())
                _isLoadingListTasksQuests.postValue(true)
            } catch (e: Exception) {
                Log.d("LoadException", e.toString())
                _isLoadingListTasksQuests.postValue(false)
            }
        }
    }

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