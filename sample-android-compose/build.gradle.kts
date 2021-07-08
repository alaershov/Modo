plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.github.terrakok.androidcomposeapp"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
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
    implementation(project(":modo-render-android-compose"))

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    implementation("com.google.android.material:material:1.4.0")

    implementation("androidx.compose.ui:ui:1.0.0-rc01")
    implementation("androidx.compose.material:material:1.0.0-rc01")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-rc01")
    implementation("androidx.activity:activity-compose:1.3.0-rc01")
}
