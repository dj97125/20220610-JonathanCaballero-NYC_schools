package com.example.nyc_schools_test.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyc_schools_test.R
import com.example.nyc_schools_test.common.BaseFragment
import com.example.nyc_schools_test.common.OnSchoolClicked
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.databinding.FragmentListSchoolBinding
import com.example.nyc_schools_test.model.remote.SchoolListResponse


class FragmentListSchool : BaseFragment(), OnSchoolClicked {

    private val binding by lazy {
        FragmentListSchoolBinding.inflate(layoutInflater)
    }
    private val nycdapter by lazy {
        NYCAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Apply the linear vertical oriented layout manager to the recycler view
        // And attach its adapter
        binding.schoolsRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = nycdapter
        }

        // Observe the list of school data and populate the view based on the network response
        // If time allowed, will populate local data when device is offline
        nycViewModel.schoolResponse.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateAction.LOADING -> {
                    showToastMessage("LOADING...")
                    binding.progressBar.visibility = View.VISIBLE
                    binding.schoolsRecycler.visibility = View.GONE
                    binding.swipeRefresh.visibility = View.GONE
                }
                is StateAction.SUCCESS<*> -> {
                    val retrievedSchools = state.response as List<SchoolListResponse>
                    binding.progressBar.visibility = View.GONE
                    binding.schoolsRecycler.visibility = View.VISIBLE
                    binding.swipeRefresh.visibility = View.VISIBLE

                    // set the fetched data to the adapter to populate the recyclerview
                        nycdapter.updateData(retrievedSchools)

                }
                is StateAction.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.schoolsRecycler.visibility = View.GONE
                    binding.swipeRefresh.visibility = View.GONE

                    displayErrors(state.error.localizedMessage) {
                        nycViewModel.getSchoolList()
                    }
                }
            }
        }

        nycViewModel.getSchoolList()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.swipeRefresh.apply {
            setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_purple,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
            )
            setOnRefreshListener {
                nycViewModel.getSchoolList()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    override fun schoolClicked(school: SchoolListResponse) {
        nycViewModel.school = school
        findNavController().navigate(R.id.action_fragmentListSchool_to_fragmentDetailsSchool)

    }
}