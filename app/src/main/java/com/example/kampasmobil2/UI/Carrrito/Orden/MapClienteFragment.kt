package com.example.kampasmobil2.UI.Carrrito.Orden

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.Blank4Args
import com.example.kampasmobil2.databinding.FragmentMapClienteBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import java.lang.Exception


class MapCliente : Fragment(R.layout.fragment_map_cliente), OnMapReadyCallback {

    val TAG = "MapCliente"

    private lateinit var binding: FragmentMapClienteBinding
    private lateinit var googleMap: GoogleMap
    val perimsio_Id = 4
    var city = ""
    var country = ""
    var adrress = ""
    var adressLatlng: LatLng? = null
    var observer=false
    var fusedLocation: FusedLocationProviderClient? = null

    private val locationCallbac = object : LocationCallback() {
        override fun onLocationResult(dtbs: LocationResult) {
            val datos = dtbs.lastLocation
            for (location in dtbs.locations) {
                Log.d(TAG, "Se recibió una actualización $datos")
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireContext())
        val fragment = childFragmentManager.findFragmentById(R.id.fragmentMap_Cliente) as SupportMapFragment
        fragment.onCreate(savedInstanceState)
        fragment.onResume()
        fragment.getMapAsync(this)
        if (observer){
            lastLocation()
            observer=true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_map_cliente, container, false)
        val fragment =
            childFragmentManager.findFragmentById(R.id.fragmentMap_Cliente) as SupportMapFragment
        fragment.onCreate(savedInstanceState)
        fragment.onResume()
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireContext())
        fragment.getMapAsync(this)
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapClienteBinding.bind(view)
        val fragment =
            childFragmentManager.findFragmentById(R.id.fragmentMap_Cliente) as SupportMapFragment
        fragment.onCreate(savedInstanceState)
        fragment.onResume()
        fragment.getMapAsync(this)
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.buttonOn.setOnClickListener {
            backDireccion()
        }
    }

    override fun onStart() {
        super.onStart()
        val fragment = childFragmentManager.findFragmentById(R.id.fragmentMap_Cliente) as SupportMapFragment
        fragment.onResume()
        fragment.getMapAsync(this)

        fusedLocation = LocationServices.getFusedLocationProviderClient(requireContext())
        lastLocation()
    }

    /*
        override fun onPause() {
            super.onPause()
            fusedLocation = LocationServices.getFusedLocationProviderClient(requireContext())
            lastLocation()
        }
        override fun onStart() {
            super.onStart()
            fusedLocation = LocationServices.getFusedLocationProviderClient(requireContext())
            lastLocation()
        }
        */
    override fun onMapReady(map: GoogleMap) {
        map.let {
            googleMap = it
            oncameraMove()
        }
    }

    private fun oncameraMove() {

        googleMap.let {
            it.setOnCameraIdleListener {
                try {
                    val geoCoder = Geocoder(requireContext())
                    adressLatlng = googleMap?.cameraPosition?.target
                    val adressList = geoCoder.getFromLocation(
                        adressLatlng?.latitude!!,
                        adressLatlng?.longitude!!,
                        1
                    )
                    city = adressList[0].locality
                    adrress = adressList[0].getAddressLine(0)
                    country = adressList[0].countryName
                    binding.textDireccionMap.text = "$adrress, $city"
                    Log.d(TAG, "oncameraMove: $country, $city, $adrress")

                } catch (e: Exception) {
                    Log.d(TAG, "oncameraMove: ${e.message}")
                }
            }
        }
    }

    private fun backDireccion() {

    }

    private fun lastLocation() {
        if (checkPermission()) {

            if (islocationEnable()) {
                fusedLocation?.lastLocation!!.addOnCompleteListener { task ->
                    var location = task.result
                    if (location == null) {
                        newLocationData()
                        Log.d(TAG, "lastLocation: ${location}")
                    } else {
                        googleMap?.moveCamera(
                            CameraUpdateFactory.newCameraPosition(
                                CameraPosition.builder().target(
                                    LatLng(location.latitude, location.longitude)
                                ).zoom(15f).build()
                            )
                        )
                    }
                }
            } else {
                Toast.makeText(context, "habilita la localizacion", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermsion()
        }
    }

    private fun newLocationData() {
        val location = com.google.android.gms.location.LocationRequest.create().apply {
            interval = 100
            fastestInterval = 50
            priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocation?.requestLocationUpdates(location, locationCallbac, Looper.myLooper()!!)
    }

    private fun islocationEnable(): Boolean {

        var locationManager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )

    }

    private fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermsion() {
        ActivityCompat.requestPermissions(
            requireContext() as Activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ), perimsio_Id
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == perimsio_Id) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                lastLocation()
            }
        }
    }
}