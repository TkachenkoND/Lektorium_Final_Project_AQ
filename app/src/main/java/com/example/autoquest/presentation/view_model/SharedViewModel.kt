package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
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
    private val userSignOutUseCase: UserSignOutUseCase,
    //private val addQuestToListOfTraveledUseCase: AddQuestToListOfTraveledUseCase
    private val addPointsToUserUseCase: AddPointsToUserUseCase,
    //private val fetchUsersResultUseCase: FetchUsersResultUseCase
) : ViewModel() {

    private val _questId = MutableStateFlow(0)
    val questId: StateFlow<Int> = _questId

    fun setQuestId(questId: Int) {
        _questId.value = questId
    }

    private val _taskId = MutableStateFlow(0)
    val taskId: MutableStateFlow<Int> = _taskId

    fun setQuestTaskId(taskId: Int) {
        _taskId.value = taskId
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

    fun fetchUserFavoriteQuests(userId: String) {
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

    fun fetchTaskListByQuestIdFromFbVm(questId: Int) {
        val taskListByQuestId = mutableListOf<QuestTask>()

        viewModelScope.launch {
            fetchTaskListFromFbUseCase.execute().collect { questTaskList ->
                questTaskList.questTaskList.forEach { task ->
                    if (task.questsId == questId)
                        taskListByQuestId.add(task)
                }
                _questTaskList.value = questTaskList
            }
        }
    }

    fun fetchTaskByIdVm(id: Int): QuestTask? {
        _questTaskList.value.questTaskList.forEach { questTask ->
            if (questTask.taskId == id)
                return questTask
        }
        return null
    }

    fun fetchTaskListSizeByQuestId(questsId: Int): Int {
        var count = 0

        _questTaskList.value.questTaskList.forEach {
            if (it.questsId == questsId)
                count++
        }

        return count
    }

    fun addQuestToFavourites(
        userId: String,
        questId: Int,
        changeCallback: (result: Boolean) -> Unit
    ) {
        addQuestToFavouritesUseCase.execute(userId, questId, changeCallback)
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
        else
            _user.value = null
    }

    fun userSignOut() {
        userSignOutUseCase.execute()
    }

    /* fun addQuestToListOfTraveledByUserId(questsId: Int) {
         addQuestToListOfTraveledUseCase.execute(questsId)
     }*/

    fun fetchListOfTraveledByUserId(userId: String) {

    }

    fun addPointsToUser(userPoint: Float, questId: Int) {
        val userId = _user.value!!.userId
        addPointsToUserUseCase.execute(userId, userPoint, questId)
    }

    private val _resultList = MutableStateFlow(listOf<UserResultModel>())
    val resultList: StateFlow<List<UserResultModel>> = _resultList

   /* fun fetchUsersResult() {
        viewModelScope.launch {
            fetchUsersResultUseCase.execute().collect { usersResult ->
                _resultList.value = usersResult
            }

        }
    }*/


}