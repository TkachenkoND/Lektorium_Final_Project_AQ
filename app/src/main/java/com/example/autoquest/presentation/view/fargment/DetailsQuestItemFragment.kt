package com.example.autoquest.presentation.view.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.autoquest.databinding.DetailsQuestItemFragmentBinding
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsQuestItemFragment : BaseFragment<DetailsQuestItemFragmentBinding>(ListOfQuestsFragment()) {

    private val sharedVm by sharedViewModel<QuestSharedViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DetailsQuestItemFragmentBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserveQuestId()
    }

    private fun initObserveQuestId() {
        lifecycleScope.launch {
            sharedVm.questItemList.collect {

            }
        }
    }



}