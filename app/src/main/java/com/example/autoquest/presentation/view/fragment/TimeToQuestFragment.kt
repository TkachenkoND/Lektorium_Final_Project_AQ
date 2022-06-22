package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.autoquest.databinding.QuestFragmentBinding
import com.example.autoquest.domain.models.QuestItem
import com.example.autoquest.presentation.view_model.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class TimeToQuestFragment : BaseFragment<QuestFragmentBinding>(DetailsQuestItemFragment()) {

    private val sharedVm by sharedViewModel<SharedViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = QuestFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserveQuestId()
    }

    private fun initObserveQuestId() {
        lifecycleScope.launch {
            sharedVm.questId.collect { id ->
                val questItem = sharedVm.fetchQuestItemFromFbVm(id)
                if (questItem != null)
                    initTimeToQuestUi(questItem)
            }
        }
    }

    private fun initTimeToQuestUi(questItem: QuestItem) {
        val currentTime = Calendar.getInstance().time
        val endDateDay = "${questItem.dataQuest} ${questItem.timeQuest}"
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

                try {
                    binding.timeQuest.text =
                        "$elapsedDays днів $elapsedHours:$elapsedMinutes:$elapsedSeconds"
                } catch (e: Exception) {
                    this.cancel()
                }

            }

            override fun onFinish() {
                initUiToFinishTimer(questItem)
            }
        }.start()

    }

    private fun initUiToFinishTimer(questItem: QuestItem) {
        binding.apply {
            questTimeLayout.visibility = View.GONE
            questCodeLayout.visibility = View.VISIBLE

            btnConfirm.setOnClickListener {
                if (questCodeEdit.text.toString() == questItem.accessCode) {

                    //sharedVm.addQuestToListOfTraveled(questItem.questsId)

                    goToNextFragment(QuestTaskAndLocationFragment())
                } else {
                    Toast.makeText(requireContext(), "Невірний код(", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}