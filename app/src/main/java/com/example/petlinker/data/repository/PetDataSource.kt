package com.example.petlinker.data.repository

import com.example.petlinker.data.entity.Pet
import com.example.petlinker.utils.ResourceState
import kotlinx.coroutines.flow.Flow

interface PetDataSource {

    suspend fun addPet(pet: Pet)

    suspend fun updatePet(pet: Pet)

    suspend fun deletePet(petId: String)

    suspend fun getAllPets(node:String): Flow<ResourceState<List<Pet>>>


    }

