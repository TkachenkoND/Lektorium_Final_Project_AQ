package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.usecases.AddQuestToFavouritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListOfQuestsViewModel(
    private val addQuestToFavouritesUseCase: AddQuestToFavouritesUseCase
) : ViewModel() {

    private val _onlyFavoriteQuests = MutableStateFlow(QuestsItemList(emptyList()))
    val onlyFavoriteQuests: StateFlow<QuestsItemList> = _onlyFavoriteQuests


    fun addQuestToFavourites(userId: Int, questId: Int) {
        viewModelScope.launch {
            addQuestToFavouritesUseCase.execute(userId,questId)
        }
    }
}