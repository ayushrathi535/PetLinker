package com.example.petlinker.domain.viewmodel

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.utils.LocationHelper
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private  val locationHelper: LocationHelper
) : ViewModel() {


    val isLocationEnabled = MutableStateFlow(false)

    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation: StateFlow<Location?> = _currentLocation



    init {
        updateLocationServiceStatus()
    }


    fun updateCurrentLocationData(context: Context) {

        Log.e("inside updatecurrent lcoation method","im inside ")
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        // Check if location permissions are granted
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                val locationRequest = LocationRequest.create().apply {
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                    interval = 50000 // Update interval in milliseconds
                }

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        p0 ?: return
                        for (location in p0.locations) {
                            // Update ViewModel or perform any action with the obtained location
                            _currentLocation.value = location
                            Log.d("Location", "Latitude: ${location.latitude}, Longitude: ${location.longitude}")

                           val address= getReadableLocation(location.latitude,location.longitude,context)
                            val preferenceManager=PreferenceManager(context)
                            preferenceManager.userLocation(address)

                        }
                    }
                }

                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper() // Looper from main thread
                )
            } catch (e: SecurityException) {
                Log.e("Location", "SecurityException: ${e.message}")
            }
        } else {
 //            Request location permissions
            val REQUEST_LOCATION_PERMISSION=101
            askPermissions(
                context as Activity, REQUEST_LOCATION_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION
            )
           // Log.e("Location", "SecurityException: ${e.message}")

        }
    }





    private fun updateLocationServiceStatus() {
        isLocationEnabled.value = locationHelper.isConnected()
    }

    fun enabledLocationRequest(
        context: Context,
        makeRequest: (intentSenderRequest: IntentSenderRequest) -> Unit
    ) {

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            50000
        ).build()

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client: SettingsClient = LocationServices.getSettingsClient(context)
        val task: Task<LocationSettingsResponse> =
            client.checkLocationSettings(builder.build())//Checksettings with building a request

        task.addOnSuccessListener { response ->
            Log.d(
                "Location",
                "enableLocationRequest: LocationService Already Enabled"
            )
        }
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(exception.resolution)
                            .build()//Create the request prompt
                    makeRequest(intentSenderRequest)//Make the request from UI
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }
}


fun getReadableLocation(latitude: Double, longitude: Double, context: Context): String {
    var addressText = ""
    val geocoder = Geocoder(context, Locale.getDefault())

    try {

        val addresses = geocoder.getFromLocation(latitude, longitude, 1)

        if (addresses?.isNotEmpty() == true) {
            val address = addresses[0]
            addressText = "${address.getAddressLine(0)}, ${address.locality}"

            Log.d("postal code", address.postalCode)
            val preferenceManager=PreferenceManager(context)
         preferenceManager.userDivision(address.subAdminArea)
            // Use the addressText in your app
            Log.d("geolocation", addressText)
            Log.d("sub Locatily", address.subAdminArea)
        }

    } catch (e: IOException) {
        Log.d("geolocation", e.message.toString())

    }

    return addressText

}


fun askPermissions(
    activity: Activity,
    requestCode: Int,
    vararg permissions: String
) {
    ActivityCompat.requestPermissions(activity, permissions, requestCode)


}



fun hasPermissions(context: Context, vararg permissions: String): Boolean {
    for (permission in permissions) {
        if (ActivityCompat.checkSelfPermission(context, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
    }
    return true
}




data class LatandLong(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)