plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    implementation(SdDependency.Androidx.core)
    implementation(SdDependency.Androidx.lifecycle)
    implementation(SdDependency.Androidx.activity)

    implementation(platform(SdDependency.Androidx.Compose.Bom.bom))
    implementation(SdDependency.Androidx.Compose.Bom.ui)
    implementation(SdDependency.Androidx.Compose.Bom.uiGraphics)
    implementation(SdDependency.Androidx.Compose.Bom.uiToolingPreview)
    implementation(SdDependency.Androidx.Compose.Bom.material3)

    implementation(SdDependency.Androidx.Compose.navigationCompose)
    implementation(SdDependency.Androidx.Compose.hiltNavigationCompose)

    implementation(SdDependency.Androidx.splashscreen)
    implementation(SdDependency.Androidx.constraintlayoutCompose)

    implementation(SdDependency.navigationAnimation)

    implementation(SdDependency.coil)
    implementation(SdDependency.gson)

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
    namespace = "wskim.aos.simpledutch"
    compileSdk = AppConfig.targetSdk

    defaultConfig {
        applicationId = "wskim.aos.simpledutch"

        minSdk = AppConfig.sdkMin
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        buildToolsVersion = AppConfig.buildToolsVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = AppConfig.javaVersion
        targetCompatibility = AppConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}