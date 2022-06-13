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
import com.example.autoquest.presentation.view_model.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsQuestItemFragment :
    BaseFragment<DetailsQuestItemFragmentBinding>(ListOfQuestsFragment()),
    ClickDialogBtn {

    private val sharedVm by sharedViewModel<SharedViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DetailsQuestItemFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRegisteredUser()
        initObserveQuestId()
    }

    private fun initRegisteredUser() {
        //lifecycleScope.launch {
            //sharedVm.isRegistered.collect {
               // if (it) {
                    binding.universalBtn.text = getString(R.string.participate_btn_txt)
                    setClickBtnListener(true)
               // } else {
                   // binding.universalBtn.text = getString(R.string.registerTxtBtn)
                   // setClickBtnListener(it)
               // }
           // }
       // }
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
            standardInitDetailsUi(questItem)
        } else {
            //fetch data from database
        }
    }

    private fun standardInitDetailsUi(questItem: QuestItem) {
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

        }
    }


    private fun setClickBtnListener(isRegistered: Boolean) {
        binding.universalBtn.setOnClickListener {
            if (isRegistered) {
                sharedVm.setQuestTaskId(0)
                ConfirmationDialog(this as ClickDialogBtn)
                    .show(
                        childFragmentManager,
                        ConfirmationDialog.TAG
                    )
            } else
                goToNextFragment(RegisterFragment())
        }
    }

    override fun dialogBtnPress() {
        //add quest id in list
        goToNextFragment(TimeToQuestFragment())
    }

}