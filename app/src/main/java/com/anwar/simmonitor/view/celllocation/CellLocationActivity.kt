package com.anwar.simmonitor.view.celllocation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.GnssStatus
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anwar.simmnitor.view.satlocation.SatActivity
import com.anwar.simmonitor.R
import com.anwar.simmonitor.model.response.CellLocation
import com.anwar.simmonitor.utils.getCurrentCellInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.signal_view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class CellLocationActivity : AppCompatActivity() {

    private lateinit var viewModel: CellLocationViewModel
    private val signal = 0
    var mGnssStatusCallback: GnssStatus.Callback? = null
    var mLocationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val wifitxtv = findViewById<View>(R.id.wifitext) as TextView
        button_sat.setOnClickListener {
            val intent = Intent(this, SatActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        val wifiManager =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getApplicationContext().getSystemService(WIFI_SERVICE) as WifiManager
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        val linkSpeed = wifiManager.connectionInfo.rssi
        println(linkSpeed)
        wifitxtv.text = "$linkSpeed dBm"
        showStrength()
        requestPermission()
        initViewModel()
        initLocationLiveData()
        initView()
    }



    @SuppressLint("MissingPermission")
    fun showStrength() {

        val telephonyManager =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val cellInfos =
            telephonyManager.allCellInfo //This will give info of all sims present inside your mobile

        if (cellInfos != null) {
            for (i in cellInfos.indices) {
                if (cellInfos[i].isRegistered) {
                    if (cellInfos[i] is CellInfoWcdma) {
                        val cellInfoWcdma =
                            telephonyManager.allCellInfo[0] as CellInfoWcdma
                        val cellSignalStrengthWcdma =
                            cellInfoWcdma.cellSignalStrength
                        text.text = cellSignalStrengthWcdma.dbm.toString()+" dBm"
                    } else if (cellInfos[i] is CellInfoGsm) {
                        val cellInfogsm =
                            telephonyManager.allCellInfo[0] as CellInfoGsm
                        val cellSignalStrengthGsm =
                            cellInfogsm.cellSignalStrength
                        text.text = cellSignalStrengthGsm.dbm.toString()+" dBm"
                    } else if (cellInfos[i] is CellInfoLte) {
                        val cellInfoLte =
                            telephonyManager.allCellInfo[0] as CellInfoLte
                        val cellSignalStrengthLte =
                            cellInfoLte.cellSignalStrength
                        text.text = cellSignalStrengthLte.dbm.toString()+" dBm"
                    }
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        button_find.setOnClickListener(::onClickFindLocation)
        mapView.settings.javaScriptEnabled = true
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            CellLocationViewModelFactory()
        )[CellLocationViewModel::class.java]
    }

    private fun initLocationLiveData() {
        viewModel.locationLiveData.observe(
            this,
            Observer(::onStateChanged)
        )
    }

    private fun onClickFindLocation(view: View) {
        val popupMenu = PopupMenu(this, view)

        val allCellInfo = getCurrentCellInfo(this)
        allCellInfo.forEachIndexed { index, cellInfo ->
            popupMenu.menu.add(0, index, 0, "${cellInfo.radio}")
        }

        popupMenu.setOnMenuItemClickListener {
            viewModel.fetchLocation(allCellInfo[it.itemId])
            true
        }

        popupMenu.show()
    }

    private fun onStateChanged(state: State) {
        when (state) {
            is State.Loading -> {
                progressBar.show()
                mapView.hide()
            }
            is State.Failed -> {
                progressBar.hide()
                mapView.hide()
                showToast("Error: ${state.message}")
            }
            is State.Success -> {
                progressBar.hide()
                mapView.show()
                showLocationInfo(state.response)
            }
        }
    }

    private fun showLocationInfo(cellLocation: CellLocation) {
        text_location.text = getString(
            R.string.text_location_format,
            cellLocation.latitude,
            cellLocation.longitude
        )
        text_address.text = cellLocation.address
        mapView.loadUrl(
            "https://www.google.com/maps/place/${cellLocation.latitude},${cellLocation.longitude}"
        )
    }

    private fun requestPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                2000
            )
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}

private fun View.show() {
    visibility = View.VISIBLE
}

private fun View.hide() {
    visibility = View.GONE
}
