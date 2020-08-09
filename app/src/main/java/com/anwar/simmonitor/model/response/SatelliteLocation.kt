package com.anwar.simmonitor.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//Respose example:-----
// {"info":{"satname":"NAVSTAR 77 (USA 289)","satid":43873,"transactionscount":1},"positions":[{"satlatitude":-19.73597796,"satlongitude":93.29589477,"sataltitude":20163.48,"azimuth":112.53,"elevation":-22.45,"ra":18.5427404,"dec":-30.7099324,"timestamp":1596490829,"eclipsed":false}]}

class SatelliteLocation {

    @SerializedName("info")
    @Expose
    var satInfo: SatInfo =SatInfo()

     class SatInfo(
        @SerializedName("satname")
        @Expose
        var satname: String? = "",

        @SerializedName("satid")
        @Expose
        var satid: Int? = null,

        @SerializedName("transactionscount")
        @Expose
        var transactionscount: Int? = null
    )

    @SerializedName("positions")
    @Expose
    var positions: List<Satpositions>? = null


    inner class Satpositions {
        @SerializedName("satlatitude")
        @Expose
        var satlatitude: Double? = null

        @SerializedName("satlongitude")
        @Expose
        var satlongitude: Double? = null


    }
}