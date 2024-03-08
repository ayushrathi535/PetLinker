package com.example.petlinker.data.entity

import android.location.Location
import android.net.Uri
import com.google.gson.Gson

data class Pet(
    val id:String?=null,
    val petName: String,
    val petCategory: String,
    val breed: String,
    val gender: String,
    val age: String,
    val group: String,
    val cost: String,
    val color: String,
    val description: String,
    val behaviour: List<String>,
    val training: String,
//    val mobileNumber: String,
//    val location: String,
    val weight:String,
    val user:User
){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}


data class User(
    val mobileNumber:String,
    val location:String,
    val name:String,
)


data class Location(
    val address:String,
    val division:String
)