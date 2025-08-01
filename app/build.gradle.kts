plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.turker.ecommerce"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.turker.ecommerce"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }

}

dependencies {
    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Network
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.convertergson)
    implementation(libs.gson)

    // Image
    implementation(libs.image.picasso)
    implementation(libs.image.glide)
    kapt(libs.image.glide.compiler)

    // Database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // RxJava
    implementation(libs.rxjava)
    implementation(libs.rxjava.adapter)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Preference
    implementation(libs.preference)
    implementation(libs.preference.ktx)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}