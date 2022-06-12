package com.example.autoquest.presentation.view.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.autoquest.R

interface ClickDialogBtn {
    fun dialogBtnPress()
}

class ConfirmationDialog(private val clickDialogBtn: ClickDialogBtn) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.title_dialog))
            .setMessage(getString(R.string.messege_dialog))
            .setPositiveButton(getString(R.string.positive_btn_txt_dialog)) { _, _ ->
                clickDialogBtn.dialogBtnPress()
            }
            .setNegativeButton(getString(R.string.negative_btn_txt_dialog)) { _, _ ->
            }
            .create()

    companion object {
        const val TAG = "ConfirmationDialog"
    }
}