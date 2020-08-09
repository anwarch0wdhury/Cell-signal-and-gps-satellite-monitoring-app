package com.anwar.simmonitor.utils


object URLSettings {
    const val mysatelliteid:String="40294"; //satellite id
    const val mylat:String="59.293"; // your location latitude
    const val mylong:String="18.052";// your location Longitude
    const val myapi:String="Q829HD-HKWYMW-W856LJ-Demo" // get your own api from n2yo.com by log in.
    const val satpaths:String="1" // number of satellite path point you want.

    //GET
    //https://www.n2yo.com/rest/v1/satellite/positions/43873/59.293/18.052/0/4/&apiKey=Q829HD-H999-W856LJ-Demo
     const val GET_Satellite1:String = "satellite/positions/$mysatelliteid/$mylat/$mylong/0/$satpaths/&apiKey=$myapi";
     const val GET_Process:String ="v2/process.php"
     const val BASE_URL = "https://ap1.unwiredlabs.com/"
     const val BASE_URLs = "https://www.n2yo.com/rest/v1/"

}
