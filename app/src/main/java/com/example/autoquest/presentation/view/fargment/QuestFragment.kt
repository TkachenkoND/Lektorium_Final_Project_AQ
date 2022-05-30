package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.autoquest.R
import com.example.autoquest.databinding.QuestFragmentBinding
import com.example.autoquest.presentation.view.fargment.BaseFragment
import com.example.autoquest.presentation.view.fargment.ListOfQuestsFragment
import com.example.autoquest.presentation.view.fargment.LocationFragment
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class QuestFragment : BaseFragment<QuestFragmentBinding>() {

    private val sharedVm by sharedViewModel<QuestSharedViewModel>()

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
    ) = QuestFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserveQuestIdAndLoadQuestTask()
        initObserveQuestTask()
        initObserveQuestItem()
        clickBtnCheck()

    }

    private fun initObserveQuestIdAndLoadQuestTask() {
        sharedVm.questId.observe(viewLifecycleOwner) {
            if (it != null) {
                sharedVm.getQuestItemFromDataBaseVm(it)
                //sharedVm.getQuestTaskFromDataBaseVm(it)
            } else
                Toast.makeText(requireContext(), "error id", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun initObserveQuestTask() {
        sharedVm.isCorrectCode.observe(viewLifecycleOwner) {
            if (it)
                goToNextFragment(LocationFragment())
            else
                showToast("Неправельний код доступу(")
        }
    }

    private fun initObserveQuestItem() {
        sharedVm.questItem.observe(viewLifecycleOwner) {
            if (it != null) {
                startInitUi(it.timeQuest, it.dataQuest)
            } else
                Toast.makeText(requireContext(), "error questItem", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun startInitUi(time: String, data: String) {
        val currentTime = Calendar.getInstance().time
        val endDateDay = "$data $time"
        val format1 = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
        val endDate = format1.parse(endDateDay)

        val different = endDate.time - currentTime.time

        object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val daysInMilli = hoursInMilli * 24

                val elapsedDays = diff / daysInMilli
                diff %= daysInMilli

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                binding.timeQuest.text =
                    "$elapsedDays днів $elapsedHours:$elapsedMinutes:$elapsedSeconds"
            }

            override fun onFinish() {
                binding.apply {
                    timeQuest.visibility = View.GONE
                    timeTxt.visibility = View.GONE

                    btnConfirm.visibility = View.VISIBLE
                    questCodeEdit.visibility = View.VISIBLE
                    codeTxt.visibility = View.VISIBLE
                }
            }
        }.start()

    }

    private fun clickBtnCheck() {
        binding.btnConfirm.setOnClickListener {
            sharedVm.checkAccessCode(
                binding.questCodeEdit.text.toString(),
                sharedVm.listTasksQuests.value!!.questTaskList[sharedVm.questId.value!!].accessCode
            )
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

    private fun goToNextFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.containerFragment, fragment)
        }
    }

}