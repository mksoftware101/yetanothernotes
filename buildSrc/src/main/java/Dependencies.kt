private const val kotlinVersion = "1.5.31"

object BuildDependencies {

    private object Versions {
        const val kotlinGradlePlugin = kotlinVersion
        const val androidGradlePlugin = "7.1.2"
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
    const val androidLibrary = "com.android.library"
    const val kotlinForAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "kotlin-kapt"
    const val googleServices = "com.google.gms.google-services"
    const val firebaseCrashlytics = "com.google.firebase.crashlytics"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val hilt = "dagger.hilt.android.plugin"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0.0"
}

object DefaultConfig {
    const val compileSdk = 31
    const val minSdk = 23
    const val targetSdk = 31
    const val buildToolsVersion = "31.0.0"
    const val applicationId = "com.mksoftware101.notes"
}

object Core {
    val core = ":core"
}

object Common {
    val uiComponents = ":common:ui-components"
}

object Features {
    val login = ":features:login"
    val notes = ":features:notes"
}

object Libs {
    private object Versions {
        const val material = "1.4.0"
        const val firebaseBom = "27.0.0"
        const val timber = "4.7.1"
        const val threeTenAbp = "1.3.1"
        const val bindingCollectionAdapter = "4.0.0"
        const val airbnbLottie = "3.4.0"
    }

    const val androidMaterial = "com.google.android.material:material:${Versions.material}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseAnalyticsKtx = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenAbp}"
    const val bindingCollectionAdapter =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:${Versions.bindingCollectionAdapter}"
    const val bindingCollectionAdapterRecyclerView =
        "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:${Versions.bindingCollectionAdapter}"
    const val airbnbLottie = "com.airbnb.android:lottie:${Versions.airbnbLottie}"

    val androidX = AndroidX

    object AndroidX {
        private object Versions {
            const val core = "1.7.0"
            const val appcompat = "1.4.1"
            const val constraintLayout = "2.1.3"
            const val navigationVersion = "2.4.2"
            const val navigationCompose = "1.0.0-alpha10"
            const val livedata = "2.4.1"
            const val viewmodel = "2.4.1"
            const val swipeToRefresh = "1.1.0"
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
        const val swipeToRefresh =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefresh}"
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
        const val junit = "4.13.2"
        const val extJunit = "1.1.3"
        const val espresso = "3.4.0"
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
