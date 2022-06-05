package com.example.autoquest.presentation.view.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.autoquest.R
import com.example.autoquest.databinding.DetailsQuestItemFragmentBinding
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailsQuestItemFragment : BaseFragment<DetailsQuestItemFragmentBinding>() {

    private val sharedVm by sharedViewModel<QuestSharedViewModel>()

    private var questId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goToNextFragment(ListOfQuestsFragment())
            }
        })
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DetailsQuestItemFragmentBinding.inflate(inflater,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun goToNextFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.containerFragment, fragment)
        }
    }


}