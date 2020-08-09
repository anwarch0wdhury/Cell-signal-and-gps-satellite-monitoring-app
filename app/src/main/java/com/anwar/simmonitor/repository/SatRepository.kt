package com.anwar.simmonitor.repository

import android.app.Application

import androidx.lifecycle.MutableLiveData
import com.anwar.simmonitor.model.response.SatelliteLocation
import com.anwar.simmonitor.service.ApiService

import com.google.gson.Gson
import com.swapon.simplenetworkoperationusingmvvm.model.repository.remote.ApiClient


import java.io.IOException

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SatRepository(private val application: Application) {
    private var mutableLiveData: MutableLiveData<SatelliteLocation>? = null
    private var apiInterface: ApiService? = null

    val satelliteLoctionData: MutableLiveData<SatelliteLocation>
        get() {

            if (mutableLiveData == null) {
                mutableLiveData = MutableLiveData()
            }

            apiInterface = ApiClient.apiClient?.create(ApiService::class.java)
            val call = apiInterface!!.satlocList
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                    if (response.isSuccessful && response.body() != null) {

                        val gson = Gson()
                        var satelliteLocation: SatelliteLocation? = null
                        try {
                            satelliteLocation = gson.fromJson(response.body()!!.string(), SatelliteLocation::class.java)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        mutableLiveData!!.setValue(satelliteLocation)

                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }
            })

            return mutableLiveData as MutableLiveData<SatelliteLocation>
        }
}

