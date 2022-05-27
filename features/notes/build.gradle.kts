plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinForAndroid)
    id(Plugins.kotlinKapt)
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
    implementation(Libs.androidX.livedata)
    implementation(Libs.androidX.viewmodel)
    implementation(Libs.androidX.swipeToRefresh)

    implementation(Libs.androidMaterial)

    // di
    implementation(Libs.di.hilt)
    kapt(Libs.di.hiltKapt)

    // ui-components
    implementation(project(Common.uiComponents))

    // core
    implementation(project(Core.core))

    // Back4app
    implementation(Libs.parse)
    implementation(Libs.parseCoroutines)

    // recyclerview
    implementation(Libs.bindingCollectionAdapter)
    implementation(Libs.bindingCollectionAdapterRecyclerView)

    // airbnb
    implementation(Libs.airbnbLottie)

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.extJunit)
    androidTestImplementation(TestLibs.espresso)
}