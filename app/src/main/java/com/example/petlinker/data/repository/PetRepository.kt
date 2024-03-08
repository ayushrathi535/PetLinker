package com.example.petlinker.data.repository

import com.example.petlinker.data.entity.Pet
import com.example.petlinker.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetRepository @Inject constructor(private val petDataSource: PetDataSource) {

    suspend fun addPet(pet: Pet){
        petDataSource.addPet(pet)
    }

    suspend fun updatePet(pet: Pet){
        petDataSource.updatePet(pet)
    }

    suspend fun deletePet(petId:String){
        petDataSource.deletePet(petId)
    }

     suspend fun getAllPets(node: String): Flow<ResourceState<List<Pet>>> {
        return petDataSource.getAllPets(node)
    }

}