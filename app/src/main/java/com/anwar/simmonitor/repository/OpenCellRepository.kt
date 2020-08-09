package com.anwar.simmonitor.repository


import com.anwar.simmonitor.model.request.CellInfo
import com.anwar.simmonitor.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class OpenCellRepository(
    private val service: ApiService
) {
    suspend fun getLocationByCellInfo(cellInfo: CellInfo) = flow {
        val response = service.getLocationByCellInfo(cellInfo)
        emit(response.body())
    }.flowOn(Dispatchers.IO)
}
