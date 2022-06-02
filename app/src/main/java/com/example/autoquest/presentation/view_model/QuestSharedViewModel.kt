package com.example.autoquest.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.data.helper.toQuestTaskEntity
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.usecases.*
import kotlinx.coroutines.launch
import java.lang.Exception

class QuestSharedViewModel(
    private val updateQuestIsFavoriteInFbUseCase: UpdateQuestIsFavoriteInFbUseCase,
    private val fetchQuestTaskListFromFbUseCase: FetchQuestTaskListFromFbUseCase,
    private val fetchQuestItemListFromFbUseCase: FetchQuestItemListFromFbUseCase,
    private val saveFlagUserIsAuthorizedInShared: SaveFlagUserIsAuthorizedInSharedUseCase,
    private val fetchFlagUserIsAuthorizedFromShared: FetchFlagUserIsAuthorizedFromSharedUseCase,
    private val fetchAllQuestItemFromDbUseCase: FetchAllQuestItemFromDbUseCase,
    private val fetchDataQuestItemFromDbUseCase: FetchDataQuestItemFromDbUseCase,
    private val insertQuestItemInDbUseCase: InsertQuestItemInDbUseCase,
    private val updateQuestItemInDataBaseUseCase: UpdateQuestItemInDataBaseUseCase
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

}