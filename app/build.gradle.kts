plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.kapt")
}

android {

    namespace = "com.company.kalasetu"

    compileSdk = 34

    defaultConfig {

        applicationId = "com.company.kalasetu"

        minSdk = 24
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        sourceCompatibility =
            JavaVersion.VERSION_17

        targetCompatibility =
            JavaVersion.VERSION_17
    }

    kotlinOptions {

        jvmTarget = "17"
    }

    buildFeatures {

        viewBinding = true
    }
}

dependencies {

    // Android Core

    implementation("androidx.core:core-ktx:1.13.1")

    implementation("androidx.appcompat:appcompat:1.7.0")

    implementation(
        "com.google.android.material:material:1.12.0"
    )

    implementation(
        "androidx.constraintlayout:constraintlayout:2.1.4"
    )

    implementation(
        "androidx.activity:activity-ktx:1.9.0"
    )

    // Firebase BOM

    implementation(
        platform(
            "com.google.firebase:firebase-bom:33.1.0"
        )
    )

    // Firebase Authentication

    implementation(
        "com.google.firebase:firebase-auth"
    )

    // Firebase Realtime Database

    implementation(
        "com.google.firebase:firebase-database"
    )

    // Firebase Storage

    implementation(
        "com.google.firebase:firebase-storage"
    )

    // Firebase Analytics

    implementation(
        "com.google.firebase:firebase-analytics"
    )
    implementation(
        "com.google.firebase:firebase-firestore"
    )
    implementation("com.cloudinary:cloudinary-android:2.3.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.cardview:cardview:1.0.0")

    // ML Kit

    implementation(
        "com.google.mlkit:text-recognition:16.0.0"
    )
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")
    // Testing

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation(
        "androidx.test.ext:junit:1.2.1"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.6.1"
    )
}