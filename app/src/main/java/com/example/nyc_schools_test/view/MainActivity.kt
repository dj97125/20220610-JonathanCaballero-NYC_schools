package com.example.nyc_schools_test.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyc_schools_test.common.OnSchoolClicked
import com.example.nyc_schools_test.common.SCHOOL_ITEM
import com.example.nyc_schools_test.common.StateAction
import com.example.nyc_schools_test.databinding.ActivityMainBinding
import com.example.nyc_schools_test.model.remote.SchoolListResponse
import com.example.nyc_schools_test.viewmodel.NYCViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
    }


}



