private const val kotlinVersion = "1.4.31"

object BuildDependencies {

    private object Versions {
        const val kotlinGradlePlugin = kotlinVersion
        const val androidGradlePlugin = "4.1.2"
        const val googleServicesPlugin = "4.3.5"
        const val firebaseCrashlytics = "2.5.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
    const val googleServicesPlugin =
        "com.google.gms:google-services:${Versions.googleServicesPlugin}"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlytics}"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val googleServices = "com.google.gms.google-services"
    const val firebaseCrashlytics = "com.google.firebase.crashlytics"
}

object Apps {
    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = compileSdk
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildTools = "30.0.3"
    const val applicationId = "com.mksoftware101.notes"
}

object Libs {
    private object Versions {
        const val kotlin = kotlinVersion
        const val material = "1.3.0"
        const val firebaseBom = "27.0.0"
        const val timber = "4.7.1"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val androidMaterial = "com.google.android.material:material:${Versions.material}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val androidX = AndroidX

    object AndroidX {
        private object Versions {
            const val core = "1.3.2"
            const val appcompat = "1.2.0"
            const val constraintLayout = "2.0.4"
        }

        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }
}

object TestLibs {
    private object Versions {
        const val junit = "4.13"
        const val extJunit = "1.1.2"
        const val espresso = "3.3.0"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}
