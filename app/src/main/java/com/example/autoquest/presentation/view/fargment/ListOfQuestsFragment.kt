package com.example.autoquest.presentation.view.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoquest.R
import com.example.autoquest.databinding.ListOfQuestsFragmentBinding
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.presentation.view.adapter.ClickOnTheItem
import com.example.autoquest.presentation.view.adapter.ListOfQuestsAdapter
import com.example.autoquest.presentation.view.fragment.RegisterFragment
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import kotlinx.coroutines.launch

import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListOfQuestsFragment : BaseFragment<ListOfQuestsFragmentBinding>(), ClickOnTheItem {

    private val listOfQuestsAdapter =
        ListOfQuestsAdapter(this@ListOfQuestsFragment as ClickOnTheItem)

    private val listOfQuestsVm by sharedViewModel<QuestSharedViewModel>()

    private var newFavoriteList: List<QuestItem>? = null
    private var listOfQuests: List<QuestItem>? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ListOfQuestsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRvAdapter()
        initObserveQuestItemList()
        setClickListener()
    }

    private fun initObserveQuestItemList() {
        viewLifecycleOwner.lifecycleScope.launch {
            listOfQuestsVm.questItemList.collect {

            }
        }
    }

    private fun setClickListener() {
        binding.apply {
            onlyFavoritesFilterBtn.setOnClickListener {
                listOfQuestsAdapter.submitList(newFavoriteList)
            }

            allQuestsFilterBtn.setOnClickListener {
                listOfQuestsAdapter.submitList(listOfQuests)
            }

            registerBtn.setOnClickListener {
                goToNextFragment(RegisterFragment())
            }
        }
    }

    private fun initRvAdapter() {
        binding.listOfFragRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listOfQuestsAdapter
        }
    }

    private fun goToNextFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.containerFragment, fragment)
        }
    }

    override fun itemPress(questItem: QuestItem) {
        listOfQuestsVm.setQuestId(questItem.questsId)
        goToNextFragment(DetailsQuestItemFragment())
    }

}