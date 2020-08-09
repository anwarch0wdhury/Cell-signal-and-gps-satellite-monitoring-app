package com.anwar.simmonitor.view.celllocation

import com.anwar.simmonitor.model.response.CellLocation

sealed class State {
    object Loading : State()
    data class Success(val response: CellLocation) : State()
    data class Failed(val message: String) : State()
}
