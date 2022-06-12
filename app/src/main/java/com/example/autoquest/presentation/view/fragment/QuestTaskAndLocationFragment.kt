package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.autoquest.R
import com.example.autoquest.databinding.QuestTaskAndLocationFragmentBinding
import com.example.autoquest.domain.models.QuestTask
import com.example.autoquest.presentation.view.dialog.ClickExitTheAppDialogBtn
import com.example.autoquest.presentation.view_model.QuestTaskAndLocationViewModel
import com.example.autoquest.presentation.view_model.SharedViewModel
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestTaskAndLocationFragment :
    BaseFragment<QuestTaskAndLocationFragmentBinding>(null),
    ClickExitTheAppDialogBtn {

    private val sharedVm by sharedViewModel<SharedViewModel>()
    private val questTaskAndLocationVm by viewModel<QuestTaskAndLocationViewModel>()

    private var questTaskId = 0

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = QuestTaskAndLocationFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initQuestTaskId()
    }

    private fun initQuestTaskId() {
        lifecycleScope.launch {

            sharedVm.questTaskId.collect { id ->
                val questTask = sharedVm.fetchQuestTaskFromFbVm(id)
                val questTaskListSize = sharedVm.fetchTaskListSize()
                questTaskId = id

                if (questTask != null) {
                    //initQuestTaskUi(questTask, questTaskListSize)
                    initLocationListener(questTask)
                }
            }
        }
    }

    private fun initLocationListener(questTask: QuestTask) {
        binding.questTaskFragment.visibility = View.GONE
        binding.mapLayout.visibility = View.VISIBLE

        val mapFragment = childFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as SupportMapFragment

        questTaskAndLocationVm.getLocate(questTask.latitude, questTask.longitude, mapFragment)

    }

    private fun initQuestTaskUi(questTask: QuestTask, questTaskListSize: Int) {
        binding.apply {
            mapLayout.visibility = View.GONE
            questTaskFragment.visibility = View.VISIBLE

            answerText.text = questTask.textTask

            if (questTask.questTaskImg != "") {
                Glide.with(questTaskImg.context)
                    .load(questTask.questTaskImg)
                    .error(R.color.white)
                    .into(questTaskImg)
            } else {
                questTaskImg.visibility = View.GONE
            }

            checkAnswerBtn.setOnClickListener {
                val userAnswer = binding.editUserAnswer.text.toString()

                if (userAnswer == questTask.answerTask) {
                    //addPointsToUser()

                    val nextQuestTaskId = questTaskId + 1

                    if (nextQuestTaskId < questTaskListSize) {
                        binding.questTaskFragment.visibility = View.GONE
                        binding.mapLayout.visibility = View.VISIBLE

                        sharedVm.setQuestTaskId(nextQuestTaskId)
                    } else {
                        // go to final fragment
                    }
                } else {
                    Toast.makeText(requireContext(), "Відповідь не правильна(", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

    override fun dialogBtnPress() {
        //save data and exit the app
        activity?.finish()
    }

}