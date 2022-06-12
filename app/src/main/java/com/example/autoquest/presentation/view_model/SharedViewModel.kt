package com.example.autoquest.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.domain.models.QuestsItemList
import com.example.autoquest.domain.usecases.FetchItemListFromFbUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(
    private val fetchItemListFromFbUseCase: FetchItemListFromFbUseCase,
) : ViewModel() {

    private val _questId = MutableStateFlow(0)
    val questId: StateFlow<Int> = _questId

    fun setQuestId(questId: Int) {
        _questId.value = questId
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

    fun fetchQuestItemFromFbVm(id: Int): QuestItem? {
        _questItemList.value.questItemList.forEach { questItem ->
            if (questItem.questsId == id)
                return questItem
        }
        return null
    }
}