package com.anwar.simmonitor.service

import com.anwar.simmonitor.model.request.CellInfo
import com.anwar.simmonitor.model.response.CellLocation
import com.anwar.simmonitor.utils.URLSettings
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // POST
    @POST(URLSettings.GET_Process)
    suspend fun getLocationByCellInfo(@Body cellInfo: CellInfo): Response<CellLocation>

    //GET
    @get:GET(URLSettings.GET_Satellite1)
    val satlocList: Call<ResponseBody>


}