import java.util.Properties
import java.io.File
import java.io.FileInputStream

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinForAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.googleServices)
    id(Plugins.firebaseCrashlytics)
    id(Plugins.safeArgs)
    id(Plugins.hilt)
}

android {
    compileSdk = DefaultConfig.compileSdk
    buildToolsVersion = DefaultConfig.buildToolsVersion

    defaultConfig {
        applicationId = DefaultConfig.applicationId
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        testInstrumentationRunner = TestLibs.instrumentationRunner
    }
    signingConfigs {
        create("release") {
            if (System.getenv()["CI"] == "true") {
                if (System.getenv()["FCI_KEYSTORE_PATH"] != null) {
                    storeFile = file(System.getenv()["FCI_KEYSTORE_PATH"].toString())
                }
                if (System.getenv()["FCI_KEYSTORE_PASSWORD"] != null) {
                    storePassword = System.getenv()["FCI_KEYSTORE_PASSWORD"].toString()
                }
                if (System.getenv()["FCI_KEY_ALIAS"] != null) {
                    keyAlias = System.getenv()["FCI_KEY_ALIAS"].toString()
                }
                if (System.getenv()["FCI_KEY_PASSWORD"] != null) {
                    keyPassword = System.getenv()["FCI_KEY_PASSWORD"].toString()
                }
            } else {
                val localReleaseProp = Properties()
                localReleaseProp.load(FileInputStream(File(rootProject.rootDir, "config/config_release.properities")))
                storeFile = File(rootProject.rootDir, "config/yetanothernotes_release.jks")
                storePassword = localReleaseProp["release.keystorePassword"].toString()
                keyAlias = localReleaseProp["release.keyAlias"].toString()
                keyPassword = localReleaseProp["release.keyPassword"].toString()
            }
        }
    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
        dataBinding = true
    }
}

dependencies {
    implementation(Libs.androidX.core)
    implementation(Libs.androidX.appcompat)
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.navigationFragment)
    implementation(Libs.androidX.navigationUi)
    implementation(Libs.androidX.navigationFragmentKtx)
    implementation(Libs.androidX.navigationUiKtx)
    implementation(Libs.androidX.navigationFeatureModuleSupport)
    implementation(Libs.androidX.navigationCompose)
    implementation(Libs.androidMaterial)
    implementation(Libs.androidX.livedata)
    implementation(Libs.androidX.viewmodel)
    implementation(Libs.androidX.swipeToRefresh)

    // di
    implementation(Libs.di.hilt)
    kapt(Libs.di.hiltKapt)

    // firebase
    implementation(platform(Libs.firebaseBom))
    implementation(Libs.firebaseAnalyticsKtx)
    implementation(Libs.firebaseCrashlyticsKtx)

    // timber
    implementation(Libs.timber)

    // time and date library
    implementation(Libs.threeTenAbp)

    // room
    implementation(Libs.db.room)
    kapt(Libs.db.roomKapt)
    implementation(Libs.db.roomKtx)

    // recyclerview
    implementation(Libs.bindingCollectionAdapter)
    implementation(Libs.bindingCollectionAdapterRecyclerView)

    // airbnb
    implementation(Libs.airbnbLottie)

    // tests
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.room)
    androidTestImplementation(TestLibs.extJunit)
    androidTestImplementation(TestLibs.espresso)
    androidTestImplementation(TestLibs.navigation)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}