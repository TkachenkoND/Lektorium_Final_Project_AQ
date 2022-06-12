package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.autoquest.R
import com.example.autoquest.databinding.LocationFragmentBinding
import com.example.autoquest.domain.models.QuestTask
import com.example.autoquest.presentation.view_model.DetailsQuestItemFragmentViewModel
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class QuestTaskFragment : BaseFragment<LocationFragmentBinding>(TimeToQuestFragment()) {

    private val sharedVm by sharedViewModel<DetailsQuestItemFragmentViewModel>()

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
        lifecycleScope.launch {

            sharedVm.questTaskId.collect { id ->
                val questTask = sharedVm.fetchQuestTaskFromFbVm(id)
                val questTaskListSize = sharedVm.getQuestTaskListSize

                if (questTask != null && questTaskListSize != null) {
                    //initQuestTaskUi(questTask, questTaskListSize)
                    initLocationListener(questTask)
                }
            }
        }
    }

    private fun initLocationListener(questTask: QuestTask) {

        val mapFragment = childFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as SupportMapFragment

        sharedVm.getLocate(questTask.latitude, questTask.longitude, mapFragment)

    }

    private fun initQuestTaskUi(questTask: QuestTask, questTaskListSize: Int) {
        // init quest fragment ui
        binding.answerText.text = questTask.textTask

        binding.checkAnswerBtn.setOnClickListener {
            val userAnswer = binding.editUserAnswer.text.toString()

            if (userAnswer == questTask.answerTask && questTaskId != null) {
                val nextQuestTaskId = questTaskId!! + 1

                if (nextQuestTaskId < questTaskListSize) {
                    binding.questTaskFragment.visibility = View.GONE
                    binding.mapLayout.visibility = View.VISIBLE

                    sharedVm.setQuestTaskId(nextQuestTaskId)
                }
            } else {
                // tost
            }

        }
    }

}