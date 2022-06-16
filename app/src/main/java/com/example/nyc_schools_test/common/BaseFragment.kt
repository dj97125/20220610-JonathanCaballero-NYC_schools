package com.example.nyc_schools_test.common

import android.app.AlertDialog
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nyc_schools_test.viewmodel.NYCViewModel

open class BaseFragment: Fragment() {
    protected val nycViewModel: NYCViewModel by activityViewModels()


    fun displayErrors(
        message: String = "A moment please! Working on the issues",
        retry: () -> Unit
    ) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error occurred")
            .setPositiveButton("RETRY") { dialog, _ ->
                dialog.dismiss()
                retry()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .setMessage(message)
            .create()
            .show()
    }

    fun showToastMessage(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}