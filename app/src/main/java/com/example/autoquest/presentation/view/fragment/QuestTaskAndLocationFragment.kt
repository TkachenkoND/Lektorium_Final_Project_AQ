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
    private var questId = 0
    private var taskByQuestId: QuestTask? = null
    private var questTaskListSize = 0

    private var mapFragment: SupportMapFragment? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = QuestTaskAndLocationFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserveQuestId()
        initMapFragment()
        initQuestTaskId()
    }

    private fun initMapFragment() {
        mapFragment = childFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as SupportMapFragment
    }

    private fun initObserveQuestId() {
        lifecycleScope.launch {
            sharedVm.questId.collect { id ->
                questId = id
                questTaskListSize = sharedVm.fetchTaskListSizeByQuestId(id)
                sharedVm.setQuestTaskId(0)
            }
        }
    }

    private fun initQuestTaskId() {
        lifecycleScope.launch {
            sharedVm.taskId.collect { id ->
                taskByQuestId = sharedVm.fetchTaskByIdVm(id)
                questTaskId = id

                if (taskByQuestId != null) {
                    initLocationListener(taskByQuestId!!)
                }
            }
        }
    }

    private fun initLocationListener(questTask: QuestTask) {
        binding.questTaskFragment.visibility = View.GONE
        binding.mapLayout.visibility = View.VISIBLE

        questTaskAndLocationVm.getLocate(
            questTask.latitude,
            questTask.longitude,
            mapFragment!!
        ) { result ->
            if (result!!) {
                showToast("Ви в назначеному місці")
                initQuestTaskUi(questTask)
            } else {
                showToast("Просувайтеся на вказану точку!")
            }
        }
    }

    private fun initQuestTaskUi(questTask: QuestTask) {
        binding.apply {
            binding.questTaskFragment.visibility = View.VISIBLE
            binding.mapLayout.visibility = View.GONE

            taskText.text = questTask.textTask

            if (questTask.questTaskImg != "") {
                Glide.with(questTaskImg.context)
                    .load(questTask.questTaskImg)
                    .error(R.color.white)
                    .into(questTaskImg)
            } else {
                questTaskImg.visibility = View.GONE
            }

            checkAnswerBtn.setOnClickListener {
                val userAnswer = editUserAnswer.text.toString()

                if (userAnswer == questTask.answerTask) {
                    //addPointsToUser()
                    goToNextTask()
                } else {
                    showToast("Відповідь не правильна(")
                }

            }
        }
    }

    private fun goToNextTask() {
        val nextQuestTaskId = questTaskId + 1

        if (nextQuestTaskId < questTaskListSize) {
            sharedVm.setQuestTaskId(nextQuestTaskId)
            binding.questTaskFragment.visibility = View.GONE
            binding.mapLayout.visibility = View.VISIBLE
        } else {
            // go to final fragment
        }
    }

    private fun showToast(toastTxt: String) {
        Toast.makeText(context, toastTxt, Toast.LENGTH_SHORT).show()
    }

    override fun dialogBtnPress() {
        activity?.finish()
    }

}