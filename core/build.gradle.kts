import java.util.Properties
import java.io.FileInputStream
import java.io.File

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
        getByName("debug") {
            if (System.getenv("CI") == "true") {
                resValue ("string", "parseApplicationID", System.getenv("BACK4APP_APPLICATION_ID").toString())
                resValue("string", "parseClientKey", System.getenv("BACK4APP_CLIENT_KEY").toString())
                resValue("string", "ParseApiBaseUrl", System.getenv("BACK4APP_API_URL").toString())
            } else {
                val parseProperties = Properties().also {
                    it.load(
                        FileInputStream(File(rootProject.rootDir, "core/parse.properties"))
                    )
                }
                resValue ("string", "parseApplicationID", parseProperties["applicationID"].toString())
                resValue("string", "parseClientKey", parseProperties["clientKey"].toString())
                resValue("string", "ParseApiBaseUrl", parseProperties["apiBaseUrl"].toString())
            }
        }
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
    implementation(Libs.androidMaterial)

    // Firebase
    implementation(platform(Libs.firebaseBom))
    implementation(Libs.firebaseAnalyticsKtx)
    implementation(Libs.firebaseCrashlyticsKtx)

    // DateTime
    implementation(Libs.threeTenAbp)

    // Logging
    implementation(Libs.timber)

    // Parse
    implementation(Libs.parse)

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.extJunit)
    androidTestImplementation(TestLibs.espresso)
}