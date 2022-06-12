package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.models.QuestsTasksList
import com.example.autoquest.domain.usecases.FetchFavoriteItemFromFbUseCase
import com.example.autoquest.domain.usecases.FetchItemListFromFbUseCase
import com.example.autoquest.domain.usecases.FetchTaskListFromFbUseCase
import com.example.autoquest.domain.usecases.UpdateIsFavoriteInFbUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListOfQuestsViewModel(
    private val fetchTaskListFromFbUseCase: FetchTaskListFromFbUseCase,
    private val fetchItemListFromFbUseCase: FetchItemListFromFbUseCase,
    private val fetchFavoriteItemFromFbUseCase: FetchFavoriteItemFromFbUseCase,
    private val updateIsFavoriteInFbUseCase: UpdateIsFavoriteInFbUseCase
) : ViewModel() {

    private val _onlyFavoriteQuests = MutableStateFlow(QuestsItemList(emptyList()))
    val onlyFavoriteQuests: StateFlow<QuestsItemList> = _onlyFavoriteQuests

    private val _questTaskList = MutableStateFlow(QuestsTasksList(emptyList()))
    val questTaskList: StateFlow<QuestsTasksList> = _questTaskList

    fun fetchQuestTaskListFromFbVm() {
        viewModelScope.launch {
            fetchTaskListFromFbUseCase.execute().collect { questTaskList ->
                _questTaskList.value = questTaskList
            }
        }
    }

    fun fetchFavoriteQuestItemFromFbVm() {
        viewModelScope.launch {
            fetchFavoriteItemFromFbUseCase.execute().collect { questItemList ->
                _onlyFavoriteQuests.value = questItemList
            }
        }
    }

    fun updateQuestIsFavoriteInFb(isFavorite: Boolean) {
        updateIsFavoriteInFbUseCase.execute(isFavorite)
    }
}