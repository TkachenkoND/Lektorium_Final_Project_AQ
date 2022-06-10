package com.example.autoquest.presentation.view.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.autoquest.R
import com.example.autoquest.databinding.DetailsQuestItemFragmentBinding
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsQuestItemFragment :
    BaseFragment<DetailsQuestItemFragmentBinding>(ListOfQuestsFragment()) {

    private val sharedVm by sharedViewModel<QuestSharedViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DetailsQuestItemFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserveQuestId()
    }

    private fun initObserveQuestId() {
        lifecycleScope.launch {
            sharedVm.questId.collect { id ->
                val questItem = sharedVm.fetchQuestItemFromFbVm(id)
                initDetailsUi(questItem)
            }
        }
    }

    private fun initDetailsUi(questItem: QuestItem?) {
        if (questItem != null) {
            binding.apply {
                Glide.with(detailsQuestImg.context)
                    .load(questItem.imgDetailsQuest)
                    .error(R.color.white)
                    .into(detailsQuestImg)

                ratingQuestDetail.text = "${questItem.rating}/10"
                nameQuestDetail.text = questItem.nameQuest
                dataQuestDetail.text = questItem.dataQuest
                timeQuestDetail.text = questItem.timeQuest
                descriptionQuestDetail.text = questItem.questDescription

                clickBtnRegister()
            }
        }
    }

    private fun clickBtnRegister() {
        binding.registerBtn.setOnClickListener {
            sharedVm.setQuestTaskId(0)
            goToNextFragment(QuestTaskFragment())
        }
    }

}