package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.autoquest.domain.models.*
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
    private val addQuestToFavouritesUseCase: AddQuestToFavouritesUseCase,
    private val userSignOutUseCase: UserSignOutUseCase
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

    //Google sign
    private val _user = MutableStateFlow<User?>(null)
    val user: MutableStateFlow<User?> = _user

    fun addUserInFireBase(userId: String, userName: String, userImg: String) {
        addUserInFireBaseUseCase.execute(userId, userName, userImg)

        _user.value = User(userId, userName, userImg)
    }

    fun checkUserRegisterStatusAndGetId() {
        val user = checkUserRegisterStatusAndGetIdUseCase.execute()
        if (user != null)
            _user.value = user
    }

    fun userSignOut() {
        userSignOutUseCase.execute()
    }

    fun changeBackgroundBtnFavoriteVm(questId: Int, callback: (result: Boolean) -> Unit) {
        _onlyFavoriteQuests.value.questItemList.forEach { questItem ->
            if (questItem.questsId == questId)
                callback.invoke(true)
            else
                callback.invoke(false)
        }

    }

}