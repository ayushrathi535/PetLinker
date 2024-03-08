package com.example.petlinker.utils

import com.example.petlinker.data.entity.Pet
import com.google.gson.Gson

class PetArgType : JsonNavType<Pet>() {
    override fun fromJsonParse(value: String): Pet {
        return Gson().fromJson(value, Pet::class.java)
    }

    override fun Pet.getJsonParse(): String {
       return  Gson().toJson(this)
    }

}