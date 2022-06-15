package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoquest.databinding.ListOfQuestsFragmentBinding
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.presentation.view.adapter.ClickOnTheFavorite
import com.example.autoquest.presentation.view.adapter.ClickOnTheItem
import com.example.autoquest.presentation.view.adapter.ListOfQuestsAdapter
import com.example.autoquest.presentation.view.dialog.ClickExitTheAppDialogBtn
import com.example.autoquest.presentation.view_model.ListOfQuestsViewModel
import com.example.autoquest.presentation.view_model.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

import org.koin.androidx.viewmodel.ext.android.viewModel

class ListOfQuestsFragment :
    BaseFragment<ListOfQuestsFragmentBinding>(null),
    ClickOnTheItem,
    ClickOnTheFavorite,
    ClickExitTheAppDialogBtn {

    private val listOfQuestsAdapter =
        ListOfQuestsAdapter(
            this@ListOfQuestsFragment as ClickOnTheItem,
            this@ListOfQuestsFragment as ClickOnTheFavorite
        )

    private val listOfQuestsVm by viewModel<ListOfQuestsViewModel>()
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
            sharedVm.isRegistered.collect {
                if (it)
                    binding.registerBtn.visibility = View.GONE
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

    override fun favoritePress(isFavorite: Boolean, questId: Int) {
        listOfQuestsVm.addQuestToFavourites(isFavorite, questId)
        sharedVm.fetchQuestItemListFromFbVm()
    }

    override fun dialogBtnPress() {
        activity?.finish()
    }

}