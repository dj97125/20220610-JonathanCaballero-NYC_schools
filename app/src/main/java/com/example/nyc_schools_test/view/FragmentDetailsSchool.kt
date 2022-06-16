package com.example.nyc_schools_test.view

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.nyc_schools_test.R
import com.example.nyc_schools_test.common.BaseFragment
import com.example.nyc_schools_test.common.FailedResponseException
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.databinding.FragmentDetailsSchoolBinding
import com.example.nyc_schools_test.model.remote.SchoolListResponse
import com.example.nyc_schools_test.model.remote.SchoolSatResponse


class FragmentDetailsSchool : BaseFragment() {

    private val binding by lazy {
        FragmentDetailsSchoolBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val schoolInfo = nycViewModel.school

        nycViewModel.schoolSatResponse.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateAction.SUCCESS<*> -> {
                    val scoresResponse = state.response as List<SchoolSatResponse>
                    val score: SchoolSatResponse? = scoresResponse.firstOrNull()
                    if (scoresResponse.isEmpty()) {
                        binding.apply {
                            if (schoolInfo != null) {
                                cardSAT.visibility = View.GONE
                                tvSchoolName.text = schoolInfo?.school_name
                                tvAddress.text = schoolInfo?.location
                                tvEmail.text = schoolInfo?.school_email
                                tvWebsite.text = schoolInfo?.website
                                tvOverview.text = schoolInfo?.overview_paragraph
                            }
                        }
                    } else {
                        binding.apply {
                            if (schoolInfo != null) {
                                cardSAT.visibility = View.VISIBLE
                                tvMathScores.text = score?.mathAvg
                                tvReadingScores.text = score?.readingAvg
                                tvWritingScores.text = score?.writingAvg
                                tvSchoolName.text = schoolInfo?.school_name
                                tvAddress.text = schoolInfo?.location
                                tvEmail.text = schoolInfo?.school_email
                                tvWebsite.text = schoolInfo?.website
                                tvOverview.text = schoolInfo?.overview_paragraph
                            }
                        }

                    }
                    binding.apply {
                        backButton.setOnClickListener {
                            findNavController().navigate(R.id.action_fragmentDetailsSchool_to_fragmentListSchool)
                        }
                    }
                }
                is StateAction.ERROR -> {
                    displayErrors(state.error.localizedMessage) {}
                }
                else -> {}
            }
        }
        if (schoolInfo != null) {
            nycViewModel.getSatList(schoolInfo.dbn)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}