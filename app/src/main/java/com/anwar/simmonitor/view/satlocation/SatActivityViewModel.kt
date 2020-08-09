package com.anwar.simmnitor.view.satlocation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anwar.simmonitor.model.response.SatelliteLocation
import com.anwar.simmonitor.repository.SatRepository


class SatActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val satRepository: SatRepository


    val allSats: LiveData<SatelliteLocation>
        get() = satRepository.satelliteLoctionData


    init {
        satRepository = SatRepository(application)
    }


}
