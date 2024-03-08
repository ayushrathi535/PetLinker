plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.petlinker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.petlinker"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {


//        debug {
//            isMinifyEnabled = true
//            isShrinkResources = true
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


//room
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //navigation
        val nav_version = "2.7.6"
        implementation("androidx.navigation:navigation-compose:$nav_version")

    //slider image
    implementation ("com.google.accompanist:accompanist-pager:0.28.0")

    //extended icons
    implementation("androidx.compose.material:material:1.6.1")
    implementation ("androidx.compose.material:material-icons-extended:1.6.1")

    //dagger-hilt

    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    //gson
    implementation ("com.google.code.gson:gson:2.10.1")


//
//    //Lifecycle
//    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
//    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    //Google Services & Maps
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.maps.android:maps-compose:2.9.0")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")

    //Accompanist (Permission)
    implementation ("com.google.accompanist:accompanist-permissions:0.31.3-beta")


//credential manager
implementation("androidx.credentials:credentials:1.2.0")
    //below andorid 13
    implementation("androidx.credentials:credentials-play-services-auth:1.2.0")

//animation
    implementation("com.google.accompanist:accompanist-navigation-animation:0.16.1")

    // System ui controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

}