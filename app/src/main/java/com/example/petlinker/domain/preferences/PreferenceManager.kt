package com.example.petlinker.domain.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveUserData(name: String, phoneNumber: String, password: String) {
        sharedPreferences.edit().apply {
            putString("NAME", name)
            putString("PHONE_NUMBER", phoneNumber)
            putString("PASSWORD", password)
            apply()
        }
    }

    fun getUserData(): Triple<String?, String?, String?> {
        val name = sharedPreferences.getString("NAME", null)
        val phoneNumber = sharedPreferences.getString("PHONE_NUMBER", null)
        val password = sharedPreferences.getString("PASSWORD", null)
        return Triple(name, phoneNumber, password)
    }

    fun userName(name:String){
        sharedPreferences.edit().apply(){
            putString("NAME",name)
            apply()
        }
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("NAME", null)
    }
    fun userPassword(password:String){
        sharedPreferences.edit().apply(){
            putString("PASSWORD",password)
            apply()
        }
    }

    fun getUserPassword(): String? {
        return sharedPreferences.getString("PASSWORD", null)
    }


    fun userNumber(number:String){
        sharedPreferences.edit().apply(){
            putString("PHONE_NUMBER",number)
            apply()
        }
    }

    fun getUserNumber(): String? {
        return sharedPreferences.getString("PHONE_NUMBER", null)
    }

    fun userLocation(location:String){

        sharedPreferences.edit().apply(){
            putString("LOCATION",location)
            apply()
        }
    }

    fun getUserDivision(): String? {
        return sharedPreferences.getString("Division", null)
    }
    fun userDivision(location:String){

        sharedPreferences.edit().apply(){
            putString("Division",location)
            apply()
        }
    }

    fun getUserLocation(): String? {
        return sharedPreferences.getString("LOCATION", null)
    }

    fun saveIsLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("IS_LOGGED_IN", isLoggedIn).apply()
    }

    fun getIsLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false)
    }

    fun setCameraPermission(locat: Boolean) {
        putBoolean("cameraP", locat)
    }
    fun getCameraPermission(): Boolean {
        return getBoolean("cameraP", false)
    }

    fun getBoolean(key:String, defaultValue:Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun putBoolean(key:String, defaultValue:Boolean) {

        sharedPreferences.edit().apply(){
            putBoolean(key,defaultValue)
            apply()
        }
    }



    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}
