package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoquest.databinding.ResultQuestFragmentBinding
import com.example.autoquest.presentation.view.adapter.ResultRvAdapter
import com.example.autoquest.presentation.view_model.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ResultOfTheQuestFragment : BaseFragment<ResultQuestFragmentBinding>(ListOfQuestsFragment()) {

    private val resultRvAdapter = ResultRvAdapter()

    private val sharedVm by sharedViewModel<SharedViewModel>()


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ResultQuestFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRvAdapter()
        initObserveResultItemList()
    }

    private fun initRvAdapter() {
        binding.resultRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = resultRvAdapter
        }
    }

    private fun initObserveResultItemList() {
        lifecycleScope.launch {
            sharedVm.resultList.collect {
                resultRvAdapter.submitList(it)
            }
        }
    }

}