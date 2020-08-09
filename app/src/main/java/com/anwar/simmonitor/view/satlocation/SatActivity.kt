package com.anwar.simmnitor.view.satlocation;

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.anwar.simmonitor.R
import com.anwar.simmonitor.model.response.SatelliteLocation


import com.anwar.simmonitor.view.satlocation.SatAdapter
import kotlinx.android.synthetic.main.activity_sat.*

public class SatActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sat)




        val satActivityViewModel = ViewModelProvider(this).get(SatActivityViewModel::class.java)
        satActivityViewModel.allSats.observe(this, Observer { satellitelocation ->
            Log.d(TAG, "sat.size: " + satellitelocation.positions!!.size)

            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this@SatActivity)

            val userAdapter = SatAdapter(applicationContext, satellitelocation.positions!!)
            tv_satname.text= satellitelocation.satInfo.satname.toString()
            recyclerView.adapter = userAdapter
        })

    }




    companion object {
        private val TAG = "SatActivity"
    }
}


