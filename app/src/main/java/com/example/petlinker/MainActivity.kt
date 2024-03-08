package com.example.petlinker

import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.petlinker.domain.LocationProviderChangedReceiver
import com.example.petlinker.domain.viewmodel.LocationViewModel
import com.example.petlinker.presentation.navigation.NavGraph
import com.example.petlinker.ui.theme.PetLinkerTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val locationViewModel: LocationViewModel by viewModels()
    private var br: LocationProviderChangedReceiver? = null
    private var locationRequestLauncher: ActivityResultLauncher<IntentSenderRequest>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   registerLocationRequestLauncher()
    registerBroadcastReceiver()

        setContent {
            PetLinkerTheme {
//BarColor()
                val isLocationEnabled by locationViewModel.isLocationEnabled.collectAsState()
                if (!isLocationEnabled) {
                    locationViewModel.enabledLocationRequest(this@MainActivity) {//Call this if GPS is OFF.
                        locationRequestLauncher?.launch(it)//Launch it to show the prompt.
                    }
                }
                else{
                    locationViewModel.updateCurrentLocationData(this)
                    Log.e("inside mainActivity-->"," else block->")
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val context = LocalContext.current

//RequestPermission()

                    NavGraph()

                    //  PetDetailScreen()
                }
            }
        }
    }

    private fun registerLocationRequestLauncher() {
        locationRequestLauncher =//We will create a global var
            registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
                if (activityResult.resultCode == RESULT_OK)
                //  getUserLocation(context = this)

                    locationViewModel.updateCurrentLocationData(this)//If the user clicks OK to turn on location
                else {
                    if (!locationViewModel.isLocationEnabled.value) {//If the user cancels, Still make a check and then exit the activity
                        Toast.makeText(
                            this,
                            "Location access is mandatory to use this feature!!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        //  finish()
                    }
                }
            }
    }

    private fun registerBroadcastReceiver() {
        br = LocationProviderChangedReceiver()
        br!!.init(
            object : LocationProviderChangedReceiver.LocationListener {
                override fun onEnabled() {
                    locationViewModel.isLocationEnabled.value = true//Update our VM
                }

                override fun onDisabled() {
                    locationViewModel.isLocationEnabled.value = false//Update our VM
                }
            }
        )
        val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        registerReceiver(br, filter)
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (br != null) unregisterReceiver(br)
    }


}



@Composable
private fun BarColor() {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colorScheme.background
    LaunchedEffect(color) {
        systemUiController.setSystemBarsColor(color)
    }
}
