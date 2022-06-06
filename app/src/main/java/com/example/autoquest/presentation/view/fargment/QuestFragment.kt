package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.autoquest.databinding.QuestFragmentBinding
import com.example.autoquest.presentation.view.fargment.BaseFragment
import com.example.autoquest.presentation.view.fargment.DetailsQuestItemFragment
import com.example.autoquest.presentation.view.fargment.ListOfQuestsFragment
import com.example.autoquest.presentation.view_model.QuestSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class QuestFragment : BaseFragment<QuestFragmentBinding>(DetailsQuestItemFragment()) {

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

    private fun showToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

}