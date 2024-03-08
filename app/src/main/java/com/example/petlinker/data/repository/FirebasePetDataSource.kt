package com.example.petlinker.data.repository

import android.content.Context
import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.petlinker.data.entity.Pet
import com.example.petlinker.data.entity.User
import com.example.petlinker.domain.preferences.PreferenceManager
import com.example.petlinker.utils.ResourceState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebasePetDataSource @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase, private val
    context: Context
) :
    PetDataSource {


    private val petsReference = firebaseDatabase.getReference("pets")

    private val preferenceManager = PreferenceManager(context)
    override suspend fun addPet(pet: Pet) {
        val petId = petsReference.push().key
        petId?.let {
            val petWithId = pet.copy(id = it)
            petsReference.child(pet.petCategory).child(it).setValue(petWithId)
        }
    }

    override suspend fun updatePet(pet: Pet) {
        pet.id?.let { petId ->
            petsReference.child(pet.petCategory).child(petId).setValue(pet)
        }
    }

    override suspend fun deletePet(petId: String) {
        if (petId.isNotEmpty()) {
            // Remove the pet from the database using its ID
            petsReference.child(petId).removeValue()
        }
    }

    override suspend fun getAllPets(node: String): Flow<ResourceState<List<Pet>>> {

        return callbackFlow {
            val petsRef = if (node.isNullOrEmpty()) {
                // If node is null or empty, get reference to the root level
                petsReference.child("Dog")
            } else {
                // Otherwise, get reference to the specified node
                petsReference.child(node)
            }

            val userNUmber = preferenceManager.getUserNumber()
            // Add a listener to the node
            val listener = petsRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val pets = mutableListOf<Pet>()

                    for (petSnapshot in snapshot.children) {
                        try {
                            val id = petSnapshot.child("id").getValue(String::class.java) ?: ""
                            val petName =
                                petSnapshot.child("petName").getValue(String::class.java) ?: ""
                            val petCategory =
                                petSnapshot.child("petCategory").getValue(String::class.java) ?: ""
                            val breed =
                                petSnapshot.child("breed").getValue(String::class.java) ?: ""
                            val gender =
                                petSnapshot.child("gender").getValue(String::class.java) ?: ""
                            val age = petSnapshot.child("age").getValue(String::class.java) ?: ""
                            val group =
                                petSnapshot.child("group").getValue(String::class.java) ?: ""
                            val cost = petSnapshot.child("cost").getValue(String::class.java) ?: ""
                            val color =
                                petSnapshot.child("color").getValue(String::class.java) ?: ""
                            val description =
                                petSnapshot.child("description").getValue(String::class.java) ?: ""
                            val behaviour = petSnapshot.child("behaviour")
                                .getValue(object : GenericTypeIndicator<List<String>>() {})
                                ?: emptyList()
                            val training =
                                petSnapshot.child("training").getValue(String::class.java) ?: ""
//                            val number =
//                                petSnapshot.child("mobileNumber").getValue(String::class.java) ?: ""
//                            val location =
//                                petSnapshot.child("location").getValue(String::class.java) ?: ""

                            val weight =
                                petSnapshot.child("weight").getValue(String::class.java) ?: ""

                            // Retrieve user-related information
                            val userMobileNumber = petSnapshot.child("user").child("mobileNumber").getValue(String::class.java) ?: ""
                            val userLocation = petSnapshot.child("user").child("location").getValue(String::class.java) ?: ""
                            val userName = petSnapshot.child("user").child("name").getValue(String::class.java) ?: ""



                            if (userNUmber.equals(userMobileNumber)) {
//skip the user
                            }
                            else{
                                val user = User(userMobileNumber, userLocation, userName)
                                val pet = Pet(
                                    id,
                                    petName,
                                    petCategory,
                                    breed,
                                    gender,
                                    age,
                                    group,
                                    cost,
                                    color,
                                    description,
                                    behaviour,
                                    training,
                                    user= user,
                                    weight = weight
                                )
                                pets.add(pet)
                            }

                          //  Log.e("firebase pet-->>", pet.toString())
                        } catch (e: Exception) {
                            Log.e("Firebase", "Error parsing pet data: ${e.message}")
                        }
                    }
                    // Emit the list of pets as a success state
                    trySend(ResourceState.Success(pets))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(ResourceState.Error("Something went wrong"))
                }
            })

            // Return the listener to be used to cancel the flow
            awaitClose {
                // Remove the ValueEventListener to avoid memory leaks
                petsRef.removeEventListener(listener)
            }
        }
    }
}
