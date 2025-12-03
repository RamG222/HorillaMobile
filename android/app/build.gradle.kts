plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.mantralayahr"

    // Use Flutter’s values
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        // Java 17 is recommended with recent Flutter / AGP
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        // ✅ Needed for flutter_local_notifications and other Java 8+ APIs
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    defaultConfig {
        applicationId = "com.example.mantralayahr"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        // Explicit debug config (optional but nice to be explicit)
        getByName("debug") {
            // no shrinking in debug
            isMinifyEnabled = false
            // just make sure we don't accidentally enable this
            // if you don't have it, it's false by default
            // isShrinkResources = false
        }

        getByName("release") {
            // TODO: replace with your real release signing config
            signingConfig = signingConfigs.getByName("debug")

            // ⛔ No code shrinking, no resource shrinking
            isMinifyEnabled = false
            // This line is important if you ever had shrinkResources turned on:
            // otherwise Gradle may complain.
            // If you don't have this property set anywhere, it's false by default.
            // I'm setting it explicitly for safety:
            isShrinkResources = false
        }
    }

    // Optional but common in Flutter projects to avoid duplicate license files
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

flutter {
    source = "../.."
}

dependencies {
    // 🔴 Required because we turned on core library desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    // Do NOT add kotlin-stdlib manually; Kotlin plugin provides it.
}
