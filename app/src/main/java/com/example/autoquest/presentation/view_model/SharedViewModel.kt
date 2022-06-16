package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestTask
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import com.example.autoquest.domain.usecases.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(
    private val fetchItemListFromFbUseCase: FetchItemListFromFbUseCase,
    private val fetchTaskListFromFbUseCase: FetchTaskListFromFbUseCase,
    private val fetchUserFavouriteQuestsUseCase: FetchUserFavouriteQuestsUseCase,
    private val addUserInFireBaseUseCase: SaveUserInFireBaseUseCase,
    private val checkUserRegisterStatusAndGetIdUseCase: CheckUserRegisterStatusAndGetIdUseCase,
    private val addQuestToFavouritesUseCase: AddQuestToFavouritesUseCase
) : ViewModel() {

    private val _questId = MutableStateFlow(0)
    val questId: StateFlow<Int> = _questId

    fun setQuestId(questId: Int) {
        _questId.value = questId
    }

    private val _questTaskId = MutableStateFlow(0)
    val questTaskId: MutableStateFlow<Int> = _questTaskId

    fun setQuestTaskId(questTaskId: Int) {
        _questTaskId.value = questTaskId
    }

    private val _questItemList = MutableStateFlow(QuestsItemList(emptyList()))
    val questItemList: StateFlow<QuestsItemList> = _questItemList

    fun fetchQuestItemListFromFbVm() {
        viewModelScope.launch {
            fetchItemListFromFbUseCase.execute().collect { questItemList ->
                _questItemList.value = questItemList
            }
        }
    }

    private val _onlyFavoriteQuests = MutableStateFlow(QuestsItemList(emptyList()))
    val onlyFavoriteQuests: StateFlow<QuestsItemList> = _onlyFavoriteQuests

    fun fetchUserFavouriteQuests(userId: String) {
        val favoriteQuests = mutableListOf<QuestItem>()

        viewModelScope.launch {
            fetchUserFavouriteQuestsUseCase.execute(userId).collect { listQuestsId ->
                _questItemList.value.questItemList.forEach { questItem ->
                    listQuestsId.forEach { questId ->
                        if (questItem.questsId == questId.toInt())
                            favoriteQuests.add(questItem)
                    }
                }
                _onlyFavoriteQuests.value = QuestsItemList(favoriteQuests)
            }

        }
    }

    fun fetchQuestItemFromFbVm(id: Int): QuestItem? {
        _questItemList.value.questItemList.forEach { questItem ->
            if (questItem.questsId == id)
                return questItem
        }
        return null
    }

    private val _questTaskList = MutableStateFlow(QuestsTasksList(emptyList()))
    val questTaskList: StateFlow<QuestsTasksList> = _questTaskList

    fun fetchQuestTaskListFromFbVm() {
        viewModelScope.launch {
            fetchTaskListFromFbUseCase.execute().collect { questTaskList ->
                _questTaskList.value = questTaskList
            }
        }
    }

    fun fetchQuestTaskFromFbVm(id: Int): QuestTask? {

        _questTaskList.value.questTaskList.forEach { questTask ->
            if (questTask.questsId == id)
                return questTask
        }
        return null
    }

    fun fetchTaskListSize() = _questTaskList.value.questTaskList.size

    fun addQuestToFavourites(userId: String, questId: Int) {
        addQuestToFavouritesUseCase.execute(userId, questId)
    }

    //Google signIn
    private val _userId = MutableStateFlow("")
    val userId: MutableStateFlow<String> = _userId

    fun addUserInFireBase(userId: String, userName: String, userImg: String) {
        addUserInFireBaseUseCase.execute(userId, userName, userImg)
        _userId.value = userId
    }

    fun checkUserRegisterStatusAndGetId() {
        val user = checkUserRegisterStatusAndGetIdUseCase.execute()
        if (user != null)
            _userId.value = user.userId
    }

}