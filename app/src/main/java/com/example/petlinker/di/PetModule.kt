package com.example.petlinker.di

import android.content.Context
import com.example.petlinker.data.repository.FirebasePetDataSource
import com.example.petlinker.data.repository.PetDataSource
import com.example.petlinker.data.repository.PetRepository
import com.example.petlinker.domain.viewmodel.LocationViewModel
import com.example.petlinker.domain.viewmodel.PetViewModel
import com.example.petlinker.utils.LocationHelper
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object PetModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun providePetDataSource(firebaseDatabase: FirebaseDatabase,@ApplicationContext context: Context): PetDataSource {
        return FirebasePetDataSource(firebaseDatabase,context)
    }

    @Provides
    fun providePetRepository(petDataSource: PetDataSource): PetRepository {
        return PetRepository(petDataSource)
    }

    @Provides
    fun provideMyViewModel(petRepository: PetRepository): PetViewModel {
        return PetViewModel(petRepository)
    }

    @Provides
    fun provideLocationModel(locationHelper: LocationHelper): LocationViewModel {
        return LocationViewModel(locationHelper)
    }


}