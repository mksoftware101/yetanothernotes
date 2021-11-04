private const val kotlinVersion = "1.4.31"

object BuildDependencies {

    private object Versions {
        const val kotlinGradlePlugin = kotlinVersion
        const val androidGradlePlugin = "4.1.2"
        const val googleServicesPlugin = "4.3.5"
        const val firebaseCrashlytics = "2.5.2"
        const val safeArgsGradlePlugin = "2.3.5"
        const val hiltPlugin = "2.38.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
    const val googleServicesPlugin =
        "com.google.gms:google-services:${Versions.googleServicesPlugin}"
    const val firebaseCrashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlytics}"
    const val safeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgsGradlePlugin}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltPlugin}"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val googleServices = "com.google.gms.google-services"
    const val firebaseCrashlytics = "com.google.firebase.crashlytics"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val hilt = "dagger.hilt.android.plugin"
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
        const val material = "1.3.0"
        const val firebaseBom = "27.0.0"
        const val timber = "4.7.1"
        const val threeTenAbp = "1.3.1"
    }

    const val androidMaterial = "com.google.android.material:material:${Versions.material}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"

    val androidX = AndroidX

    object AndroidX {
        private object Versions {
            const val core = "1.3.2"
            const val appcompat = "1.2.0"
            const val constraintLayout = "2.0.4"
            const val navigationVersion = "2.3.5"
            const val navigationCompose = "1.0.0-alpha10"
            const val livedata = "2.3.1"
            const val viewmodel = "2.3.1"
        }

        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment:${Versions.navigationVersion}"
        const val navigationUi = "androidx.navigation:navigation-ui:${Versions.navigationVersion}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
        const val navigationFeatureModuleSupport =
            "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigationVersion}"
        const val navigationCompose =
            "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
        const val safeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel}"
    }

    val di = Di

    object Di {
        private object Version {
            const val hilt = "2.38.1"
        }

        const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltKapt = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    }

    val db = Db

    object Db {
        private object Version {
            const val room = "2.3.0"
        }

        const val room = "androidx.room:room-runtime:${Version.room}"

        // To use Kotlin annotation processing tool (kapt)
        const val roomKapt = "androidx.room:room-compiler:${Version.room}"

        // Kotlin Extensions and Coroutines support for Room
        const val roomKtx = "androidx.room:room-ktx:${Version.room}"
    }
}

object TestLibs {
    private object Versions {
        const val junit = "4.13"
        const val extJunit = "1.1.2"
        const val espresso = "3.3.0"
        const val navigation = "2.3.5"
        const val room = "2.3.0"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val navigation = "androidx.navigation:navigation-testing:${Versions.navigation}"
    const val room = "androidx.room:room-testing:${Versions.room}"
}
