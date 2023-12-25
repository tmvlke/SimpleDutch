plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

dependencies {

    implementation(project(mapOf("path" to ":presentation:baseUiKit")))

    // Hilt
    implementation(SdDependency.Hilt.android)
    kapt(SdDependency.Hilt.androidCompiler)

    // tdd
    testImplementation(SdTestDependency.Tdd.junit5)
    testRuntimeOnly(SdTestDependency.Tdd.junit5Engine)
    testImplementation(SdTestDependency.Tdd.junit5Params)
    testRuntimeOnly(SdTestDependency.Tdd.junit5ParamsEngine)

    testImplementation(SdTestDependency.Tdd.mockk)

    testImplementation(SdTestDependency.Tdd.coroutines)

    // bdd1
    androidTestImplementation(SdTestDependency.Bdd.junit)
    androidTestImplementation(platform(SdTestDependency.Bdd.composeBom))
    androidTestImplementation(SdTestDependency.Bdd.composeJunit)

    // bdd2
    debugImplementation(SdTestDependency.Bdd.debugComposeTooling)
    debugImplementation(SdTestDependency.Bdd.debugComposeManifest)
}

android {
    namespace = "wskim.aos.storagetab"
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
        compose = true
    }

    compileOptions {
        sourceCompatibility(AppConfig.javaVersion)
        targetCompatibility(AppConfig.javaVersion)
    }

    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }

    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
}