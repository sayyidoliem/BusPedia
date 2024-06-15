plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "2.0.0"
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

    //for more material icon
    implementation (libs.androidx.material.icons.extended)

    //for core system adaptive layout
    implementation(libs.androidx.adaptive)

    //for adaptive layout navigation
    implementation(libs.androidx.adaptive.navigation)

    //for network kmp
    implementation (libs.ktor.client.android)

    //for serialization
    implementation (libs.ktor.client.json)

    //for serialization
    implementation (libs.ktor.client.serialization)

    //for logging
    implementation (libs.ktor.client.logging)

    //for state wrapper
    implementation (libs.apiresponsewrapper)

    //for network
    implementation(libs.retrofit)

    //for viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //for compose
    implementation (libs.androidx.lifecycle.runtime.compose)

    //converter json to gson
    implementation(libs.converter.gson)

    //for async
    implementation(libs.coil.compose)

    //navigation compose
    implementation(libs.androidx.navigation.compose)

    //local storage core
    implementation(libs.kotpref)

    // optional, auto initialization module
    implementation(libs.initializer)

    // optional, support saving enum value and ordinal
    implementation(libs.enum.support)

    //okhttp core
    implementation(libs.okhttp)

    // define a BOM and its version
    implementation(platform(libs.okhttp.bom))

    // define any required OkHttp artifacts without version
    implementation(libs.okhttp3.okhttp)

    //interceptor
    implementation(libs.logging.interceptor)

    //for swipe refresh
    implementation(libs.accompanist.swiperefresh)
}