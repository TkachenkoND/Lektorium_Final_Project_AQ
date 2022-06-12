package com.example.autoquest.presentation.view.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.autoquest.R

interface ClickExitTheAppDialogBtn {
    fun dialogBtnPress()
}

class ExitTheAppDialog(
    private val clickExitTheAppDialogBtn: ClickExitTheAppDialogBtn
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.title_exit_dialog))
            .setMessage(getString(R.string.messege_exit_dialog))
            .setPositiveButton(getString(R.string.positive_btn_txt_dialog)) { _, _ ->
                clickExitTheAppDialogBtn.dialogBtnPress()
            }
            .setNegativeButton(getString(R.string.negative_btn_txt_dialog)) { _, _ ->
            }
            .create()

    companion object {
        const val TAG = "ExitTheAppDialog"
    }
}