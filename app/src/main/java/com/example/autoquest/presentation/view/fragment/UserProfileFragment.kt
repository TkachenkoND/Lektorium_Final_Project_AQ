package com.example.autoquest.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.autoquest.R
import com.example.autoquest.databinding.UserProfileFragmentBinding
import com.example.autoquest.domain.models.User
import com.example.autoquest.presentation.view.dialog.ClickSignOutDialogBtn
import com.example.autoquest.presentation.view.dialog.SignOutDialog
import com.example.autoquest.presentation.view_model.SharedViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserProfileFragment : BaseFragment<UserProfileFragmentBinding>(ListOfQuestsFragment()),
    ClickSignOutDialogBtn {

    private val sharedVm by sharedViewModel<SharedViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = UserProfileFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserveUserData()
    }

    private fun initObserveUserData() {
        lifecycleScope.launch {
            sharedVm.user.collect { userData ->
                if (userData != null)
                    initUi(userData)
            }
        }
    }

    private fun initUi(userData: User) {
        binding.apply {
            Glide.with(detailsImage.context)
                .load(userData.userImg)
                .error(R.drawable.ic_no_user_img)
                .circleCrop()
                .into(detailsImage)

            detailsUserName.text = userData.userName
        }

        setBtnListener()
    }

    private fun setBtnListener() {
        binding.apply {
            btnBack.setOnClickListener { goToNextFragment(ListOfQuestsFragment()) }

            signOut.setOnClickListener {
                SignOutDialog(this@UserProfileFragment as ClickSignOutDialogBtn)
                    .show(
                        childFragmentManager,
                        SignOutDialog.TAG
                    )
            }
        }
    }

    override fun dialogBtnPress() {
        sharedVm.userSignOut()
        sharedVm.checkUserRegisterStatusAndGetId()

        goToNextFragment(ListOfQuestsFragment())
    }

}