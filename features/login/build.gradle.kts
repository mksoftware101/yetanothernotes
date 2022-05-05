plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinForAndroid)
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
}

dependencies {
    implementation(Libs.androidX.core)
    implementation(Libs.androidX.appcompat)
    implementation(Libs.androidX.annotation)
    implementation(Libs.androidMaterial)
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.livedata)
    implementation(Libs.androidX.viewmodel)

    // Common
    implementation(project(Common.resources))

    // Navigation
    implementation(Libs.androidX.navigationFragment)
    implementation(Libs.androidX.navigationUi)
    implementation(Libs.androidX.navigationFragmentKtx)
    implementation(Libs.androidX.navigationUiKtx)
    implementation(Libs.androidX.navigationFeatureModuleSupport)

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.extJunit)
    androidTestImplementation(TestLibs.espresso)
}