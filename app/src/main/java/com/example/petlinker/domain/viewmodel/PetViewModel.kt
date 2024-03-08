package com.example.petlinker.domain.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.data.repository.PetRepository
import com.example.petlinker.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(private val petRepository: PetRepository) : ViewModel() {

    private val _petWithCategory: MutableStateFlow<ResourceState<List<Pet>>> =
        MutableStateFlow(ResourceState.Loading())

    val petWithCategory: StateFlow<ResourceState<List<Pet>>> = _petWithCategory


    fun addPet(pet: Pet) {

        viewModelScope.launch {
            try {
                petRepository.addPet(pet)
            } catch (e: Exception) {
                Log.e("inside viewmodel-->>>", pet.toString())
                Log.e("inside viewmodel--->>", e.message.toString())
            }
        }
    }


    fun deletePet(id: String) {
        viewModelScope.launch {
            try {
                petRepository.deletePet(id)
            } catch (e: Exception) {
                Log.e("inside viewmodel--->>", e.message.toString())
            }
        }
    }


    fun updatePet(pet: Pet) {
        viewModelScope.launch {
            try {
                petRepository.updatePet(pet)
            } catch (e: Exception) {
                Log.e("inside viewmodel--->>", e.message.toString())
            }
        }
    }


    fun getPetList(node: String="") {
        viewModelScope.launch {
            petRepository.getAllPets(node).collectLatest{ pets ->
                _petWithCategory.value = pets
            }
        }

    }
    init {
     //   getPetList()
        Log.e("viewModel----->>",petWithCategory.value.toString())
    }


}