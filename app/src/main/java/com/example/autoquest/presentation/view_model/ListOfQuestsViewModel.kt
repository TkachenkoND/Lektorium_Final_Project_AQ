package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.usecases.FetchFavoriteItemFromFbUseCase
import com.example.autoquest.domain.usecases.UpdateIsFavoriteInFbUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListOfQuestsViewModel(
    private val fetchFavoriteItemFromFbUseCase: FetchFavoriteItemFromFbUseCase,
    private val updateIsFavoriteInFbUseCase: UpdateIsFavoriteInFbUseCase
) : ViewModel() {

    private val _onlyFavoriteQuests = MutableStateFlow(QuestsItemList(emptyList()))
    val onlyFavoriteQuests: StateFlow<QuestsItemList> = _onlyFavoriteQuests


    fun fetchFavoriteQuestItemFromFbVm() {
        viewModelScope.launch {
            fetchFavoriteItemFromFbUseCase.execute().collect { questItemList ->
                _onlyFavoriteQuests.value = questItemList
            }
        }
    }

    fun updateQuestIsFavoriteInFb(isFavorite: Boolean, questId: Int) {
        viewModelScope.launch {
            updateIsFavoriteInFbUseCase.execute(isFavorite,questId)
        }
    }
}