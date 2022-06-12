package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.autoquest.R
import com.example.autoquest.databinding.DetailsQuestItemFragmentBinding
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.presentation.view.dialog.ClickDialogBtn
import com.example.autoquest.presentation.view.dialog.ConfirmationDialog
import com.example.autoquest.presentation.view_model.DetailsQuestItemFragmentViewModel
import com.example.autoquest.presentation.view_model.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsQuestItemFragment :
    BaseFragment<DetailsQuestItemFragmentBinding>(ListOfQuestsFragment()), ClickDialogBtn {

    private val detailsVm by viewModel<DetailsQuestItemFragmentViewModel>()
    private val sharedVm by sharedViewModel<SharedViewModel>()

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

                setClickBtnListener()
            }
        }
    }

    private fun setClickBtnListener() {
        binding.registerBtn.setOnClickListener {
            goToNextFragment(RegisterFragment())
        }

        binding.participateBtn.setOnClickListener {
            detailsVm.setQuestTaskId(0)
            ConfirmationDialog(this as ClickDialogBtn).show(
                childFragmentManager,
                ConfirmationDialog.TAG
            )
        }
    }

    override fun dialogBtnPress() {
        //add quest id in list
        goToNextFragment(TimeToQuestFragment())
    }

}