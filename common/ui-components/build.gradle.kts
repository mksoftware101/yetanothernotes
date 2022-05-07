plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinForAndroid)
    id(Plugins.kotlinKapt)
    kotlin("kapt")
    id(Plugins.hilt)
}

android {
    compileSdk = DefaultConfig.compileSdk

    defaultConfig {
        minSdk = DefaultConfig.minSdk
        targetSdk = DefaultConfig.targetSdk

        testInstrumentationRunner = TestLibs.instrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
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
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(Libs.androidX.core)
    implementation(Libs.androidX.appcompat)
    implementation(Libs.androidX.navigationFragmentKtx)
    implementation(Libs.androidX.livedata)
    implementation(Libs.androidMaterial)
    implementation(Libs.androidX.viewmodel)

    // Hilt
    implementation(Libs.Di.hilt)
    kapt(Libs.Di.hiltKapt)

    // Coroutines
    implementation(Libs.coroutines)

    // Resources
    implementation(project(Common.resources))

    //Core
    implementation(project(Core.core))

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.extJunit)
    androidTestImplementation(TestLibs.espresso)
}