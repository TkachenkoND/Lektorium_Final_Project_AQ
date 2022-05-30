package com.example.autoquest.presentation.view.fargment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.example.autoquest.R
import com.example.autoquest.databinding.DetailsQuestItemFragmentBinding
import com.example.autoquest.presentation.view.dialog.ConfirmationDialog
import com.example.autoquest.presentation.view.fragment.QuestFragment
import com.example.autoquest.presentation.view.fragment.RegisterFragment
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

        initObserveIsLoadingListQuests()
        initObserveClickBtnDialog()
    }

    private fun initObserveIsLoadingListQuests() {
        sharedVm.fetchFlagUserIsAuthorizedFromShared()

        sharedVm.isRegistered.observe(viewLifecycleOwner) {
            initBtnClickListener(it)
        }

        sharedVm.isLoadingListQuests.observe(viewLifecycleOwner) {
            if (it)
                standardInitUi()
            else
                initObserveLoadingListOfQuestsFromDb()
        }
    }

    private fun initObserveLoadingListOfQuestsFromDb() {
        sharedVm.isGetAllQuestItem.observe(viewLifecycleOwner) {
            if (it) {
                standardInitUi()
            } else {
                Toast.makeText(requireContext(), "Error not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun standardInitUi() {
        sharedVm.questId.observe(viewLifecycleOwner) {
            if (it != null) {
                questId = it

                val dataQuestDetails = sharedVm.listQuestsItem.value!!.questList[it]
                binding.apply {
                    nameQuestDetail.text = dataQuestDetails.nameQuest
                    ratingQuestDetail.text =
                        "${dataQuestDetails.rating} ${getString(R.string.rating)}"
                    dataQuestDetail.text =
                        "${getString(R.string.data_quest)} ${dataQuestDetails.dataQuest}"
                    timeQuestDetail.text =
                        "${getString(R.string.time_quest)} ${dataQuestDetails.timeQuest}"
                    descriptionQuestDetail.text = dataQuestDetails.questDescription

                    Glide.with(detailsQuestImg.context)
                        .load(dataQuestDetails.imgDetailsQuest)
                        .error(R.drawable.not_img_detail_quest)
                        .into(detailsQuestImg)
                }
            } else
                Toast.makeText(requireContext(), "Error quest id", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initBtnClickListener(flag: Boolean) {
        binding.apply {
            if (flag) {
                registerBtn.text = getString(R.string.take_part)
                registerBtn.setOnClickListener {
                    ConfirmationDialog().show(childFragmentManager, ConfirmationDialog.TAG)
                }
            } else {
                registerBtn.setOnClickListener {
                    goToNextFragment(RegisterFragment())
                }
            }
        }
    }

    private fun initObserveClickBtnDialog() {
        sharedVm.isClickedBtnDialog.observe(viewLifecycleOwner) {
            if (it && questId != null) {
                sharedVm.saveQuestIdInShared(questId!!)
                Toast.makeText(requireContext(), getString(R.string.consent), Toast.LENGTH_SHORT)
                    .show()
                goToNextFragment(QuestFragment())
            }
        }
    }

    private fun goToNextFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.containerFragment, fragment)
        }
    }

    override fun onPause() {
        super.onPause()
        sharedVm.clickBtnDialog(false)
    }

}