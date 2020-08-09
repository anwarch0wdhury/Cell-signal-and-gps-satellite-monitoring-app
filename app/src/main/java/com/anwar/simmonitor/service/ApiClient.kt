package com.swapon.simplenetworkoperationusingmvvm.model.repository.remote

import android.util.Log
import com.anwar.simmonitor.service.ApiService
import com.anwar.simmonitor.utils.URLSettings

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ApiClient {


    private var retrofit: Retrofit? = null
    private val TAG = "ApiClient"


    val apiClient: Retrofit?
        get() {

            Log.d(TAG, " null")

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()


            if (retrofit == null) {
                Log.d(TAG, " null")
                retrofit = Retrofit.Builder()
                        .baseUrl(URLSettings.BASE_URLs)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
            } else {
                Log.d(TAG, "Retroit is not null: " + retrofit!!.toString())
            }

            return retrofit
        }
}
