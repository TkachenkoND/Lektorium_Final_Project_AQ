package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoquest.databinding.ListOfQuestsFragmentBinding
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.presentation.view.adapter.ChangeBtnFavorite
import com.example.autoquest.presentation.view.adapter.ClickOnBtnFavorite
import com.example.autoquest.presentation.view.adapter.ClickOnTheItem
import com.example.autoquest.presentation.view.adapter.ListOfQuestsAdapter
import com.example.autoquest.presentation.view.dialog.ClickExitTheAppDialogBtn
import com.example.autoquest.presentation.view_model.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListOfQuestsFragment :
    BaseFragment<ListOfQuestsFragmentBinding>(null),
    ClickOnTheItem,
    ClickExitTheAppDialogBtn,
    ChangeBtnFavorite,
    ClickOnBtnFavorite {

    private var localUserId: String? = null

    private val listOfQuestsAdapter =
        ListOfQuestsAdapter(
            this@ListOfQuestsFragment as ClickOnTheItem,
            this@ListOfQuestsFragment as ChangeBtnFavorite,
            this@ListOfQuestsFragment as ClickOnBtnFavorite
        )

    private val sharedVm by sharedViewModel<SharedViewModel>()

    private var listOfQuests: List<QuestItem>? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ListOfQuestsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedVm.fetchQuestTaskListFromFbVm()
        sharedVm.fetchQuestItemListFromFbVm()

        initRegisteredUser()
        initRvAdapter()
        initObserveQuestItemList()
        setClickListener()
    }

    private fun initRegisteredUser() {
        lifecycleScope.launch {
            sharedVm.userId.collect { userId ->
                if (userId.isNotEmpty()) {
                    localUserId = userId
                    binding.registerBtn.visibility = View.GONE
                    binding.btnLayout.visibility = View.VISIBLE
                } else {
                    binding.registerBtn.visibility = View.VISIBLE
                    binding.btnLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun initObserveQuestItemList() {
        lifecycleScope.launch {
            sharedVm.questItemList.collect {
                listOfQuests = it.questItemList
                listOfQuestsAdapter.submitList(it.questItemList)
                binding.progressBar.visibility = View.GONE
                binding.listFragmentLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun setClickListener() {
        binding.apply {
            onlyFavoritesFilterBtn.setOnClickListener {
                setClickOnBtnOnlyFavorite()
            }

            allQuestsFilterBtn.setOnClickListener {
                listOfQuestsAdapter.submitList(listOfQuests)
            }

            registerBtn.setOnClickListener {
                goToNextFragment(RegisterFragment())
            }
        }
    }

    private fun setClickOnBtnOnlyFavorite() {
        if (localUserId != null)
            sharedVm.fetchUserFavouriteQuests(localUserId!!)

        lifecycleScope.launch {
            sharedVm.onlyFavoriteQuests.collect {
                listOfQuestsAdapter.submitList(it.questItemList)
            }
        }
    }

    private fun initRvAdapter() {
        binding.listOfFragRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listOfQuestsAdapter
        }
    }

    override fun itemPress(questItem: QuestItem) {
        sharedVm.setQuestId(questItem.questsId)
        goToNextFragment(DetailsQuestItemFragment())
    }

    override fun dialogBtnPress() {
        activity?.finish()
    }

    override fun onStart() {
        super.onStart()
        sharedVm.checkUserRegisterStatusAndGetId()
    }

    override fun changeBackgroundBtnFavorite(questId: Int, callback: (result: Boolean) -> Unit) {
        lifecycleScope.launch {
            sharedVm.onlyFavoriteQuests.collect {
                it.questItemList.forEach { questItem ->
                    if (questItem.questsId == questId)
                        callback.invoke(true)
                    else
                        callback.invoke(false)
                }
            }
        }
    }

    override fun clickBtnFavorite(questsId: Int) {
        sharedVm.addQuestToFavourites(localUserId!!, questsId)
    }

}