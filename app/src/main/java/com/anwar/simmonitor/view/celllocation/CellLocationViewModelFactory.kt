package com.anwar.simmonitor.view.celllocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anwar.simmonitor.repository.OpenCellRepository
import com.anwar.simmonitor.service.buildUnwiredLabsService
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CellLocationViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CellLocationViewModel::class.java)) {
            return CellLocationViewModel(
                OpenCellRepository(buildUnwiredLabsService())
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }




}
