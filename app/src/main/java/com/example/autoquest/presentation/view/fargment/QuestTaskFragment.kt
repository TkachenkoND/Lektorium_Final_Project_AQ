package com.example.autoquest.presentation.view.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.autoquest.R
import com.example.autoquest.databinding.LocationFragmentBinding
import com.example.autoquest.domain.models.QuestTask
import com.example.autoquest.presentation.view.fragment.QuestFragment
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class QuestTaskFragment : BaseFragment<LocationFragmentBinding>(QuestFragment()) {

    private val sharedVm by sharedViewModel<QuestSharedViewModel>()

    private var questTaskId: Int? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = LocationFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initQuestTaskId()
    }

    private fun initQuestTaskId() {
        sharedVm.fetchQuestTaskListFromFbVm()

        lifecycleScope.launch {

            sharedVm.questTaskId.collect { id ->
                val questTask = sharedVm.fetchQuestTaskFromFbVm(id)
                val questTaskListSize = sharedVm.getQuestTaskListSize

                initQuestTaskUi(questTask!!, 0)
               initLocationListener(questTask)
            }
        }
    }

    private fun initLocationListener(questTask: QuestTask) {

        val mapFragment = childFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as SupportMapFragment

        if (sharedVm.getLocate(questTask.latitude, questTask.longitude, mapFragment)) {
            binding.questTaskFragment.visibility = View.VISIBLE
            binding.mapFragment.visibility = View.GONE
            binding.questTaskText.visibility = View.VISIBLE
        }

    }

    private fun initQuestTaskUi(questTask: QuestTask, questTaskListSize: Int) {
        // init quest fragment ui
        binding.answerText.text = questTask.answerTask

        val userAnswer = binding.editUserAnswer.text.toString()

        binding.checkAnswerBtn.setOnClickListener {
            if (userAnswer == questTask.answerTask && questTaskId != null) {
                val nextQuestTaskId = questTaskId!! + 1

                if (nextQuestTaskId < questTaskListSize) {
                    binding.questTaskFragment.visibility = View.GONE
                    binding.mapFragment.visibility = View.VISIBLE

                    sharedVm.setQuestTaskId(nextQuestTaskId)
                }
            } else {
                // tost
            }

        }
    }

}