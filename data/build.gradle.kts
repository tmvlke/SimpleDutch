plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

dependencies {
    implementation(project(":domain"))

    // SharedPreferences
    implementation(SdDependency.Repository.preference)

    // hilt
    implementation(SdDependency.Hilt.android)
    kapt(SdDependency.Hilt.androidCompiler)

    // tdd
    testImplementation(SdTestDependency.Tdd.junit5)
    testRuntimeOnly(SdTestDependency.Tdd.junit5Engine)
    testImplementation(SdTestDependency.Tdd.junit5Params)
    testRuntimeOnly(SdTestDependency.Tdd.junit5ParamsEngine)

    testImplementation(SdTestDependency.Tdd.mockk)
}

android {
    namespace = "wskim.aos.data"
    compileSdk = AppConfig.targetSdk

    defaultConfig {
        minSdk = AppConfig.sdkMin
        buildToolsVersion = AppConfig.buildToolsVersion
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        // 기존 BuildConfig 는 사용 되지 않음
        buildConfig = false
    }

    compileOptions {
        sourceCompatibility(AppConfig.javaVersion)
        targetCompatibility(AppConfig.javaVersion)
    }

    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
}