package com.prateekshah.prateekshahgrowghassignment

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.prateekshah.prateekshahgrowghassignment.databinding.FragmentMapBinding
import com.prateekshah.prateekshahgrowghassignment.databinding.FragmentUploadImageBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment() {

    private var binding: FragmentMapBinding? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val REQUEST_CODE = 100
    var gps_enabled = false
    var network_enabled = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val fragmentBinding = FragmentMapBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.fragment = this
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        //getCurrentLocationUser()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        if (gps_enabled && network_enabled) {
            getLastLocation(mapFragment!!)
        }else{
            turnOnLocation()
            getLastLocation(mapFragment!!)
        }
    }

    override fun onResume() {
        super.onResume()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        if (gps_enabled && network_enabled) {
            getLastLocation(mapFragment!!)
        }else{
            turnOnLocation()
            getLastLocation(mapFragment!!)
        }
    }
    private fun getLastLocation(mapFragment: SupportMapFragment) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_CODE
            )
        } else {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    mapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
                        try {
                            val latLng = LatLng(location!!.latitude, location.longitude)
                            val markerOptions = MarkerOptions().position(latLng).title("Current Location")
                            googleMap.addMarker(markerOptions)
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
                        }catch (e:Exception){
                            Log.e("fusedLocation", e.message.toString())
                        }
                    })
                }
                .addOnFailureListener {
                    Log.e("fusedLocation", it.message.toString())
                }
        }
    }

    private fun turnOnLocation() {
        val lm = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
            Log.e("fusedLocation", ex.message.toString())
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
            Log.e("fusedLocation", ex.message.toString())
        }
        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder(requireContext())
                .setMessage("Gps not enabled")
                .setPositiveButton(
                    "Give Permission",
                    DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
                        requireContext().startActivity(
                            Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        )
                    })
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
    fun goToFeedsFragment() {
        findNavController().navigateUp()
    }
}