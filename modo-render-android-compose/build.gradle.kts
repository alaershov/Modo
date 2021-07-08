plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30
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
        kotlinCompilerExtensionVersion = "1.0.0-rc01"
    }
}

dependencies {
    implementation(project(":modo"))

    implementation("androidx.lifecycle:lifecycle-common:2.3.1")

    implementation("androidx.compose.ui:ui:1.0.0-rc01")
    implementation("androidx.compose.foundation:foundation:1.0.0-rc01")
}