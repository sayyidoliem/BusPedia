plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.olimhousestudio.buspedia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.olimhousestudio.buspedia"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//    //for viewmodel
//    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.compose)
//
//    //type-safe http client
//    implementation("com.squareup.retrofit2:retrofit")
//
//    //converter json to gson
//    implementation(libs.converter.gson)
//
//    //for async
//    implementation(libs.coil.compose)
//
//    //for core system adaptive layout
//    implementation(libs.androidx.adaptive)
//
//    //for adaptive layout
//    implementation(libs.androidx.adaptive.layout)
//
//    //for adaptive layout navigation
//    implementation(libs.androidx.adaptive.navigation)
//
//    //for more material icon
//    implementation (libs.androidx.material.icons.extended)

//    //for collectAsState with lifecycle
//    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.androidx.adaptive)

    implementation(libs.androidx.adaptive.layout)

    implementation(libs.androidx.adaptive.navigation)

    //for network kmp
    implementation (libs.ktor.client.android)
    implementation (libs.ktor.client.json)
    implementation (libs.ktor.client.serialization)
    implementation (libs.ktor.client.logging)

    //for state wrapper
    implementation(libs.apiresponsewrapper)

    //for network
    implementation(libs.retrofit)

    //for viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //converter json to gson
    implementation(libs.converter.gson)

    implementation(libs.coil.compose)
}