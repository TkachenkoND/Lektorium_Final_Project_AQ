package com.example.autoquest.presentation.view.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.example.autoquest.R
import com.example.autoquest.presentation.view.dialog.ClickExitTheAppDialogBtn
import com.example.autoquest.presentation.view.dialog.ExitTheAppDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

abstract class BaseFragment<B : ViewBinding>(private val fragment: Fragment?) : Fragment() {

    private var _viewBinding: B? = null
    protected val binding get() = checkNotNull(_viewBinding)

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B

    protected fun goToNextFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.containerFragment, fragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (fragment == null) {
                    ExitTheAppDialog(this@BaseFragment as ClickExitTheAppDialogBtn).show(
                        childFragmentManager,
                        ExitTheAppDialog.TAG
                    )
                } else
                    goToNextFragment(fragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = initBinding(inflater, container)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}