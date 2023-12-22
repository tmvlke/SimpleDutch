import java.util.Calendar
import java.util.Date

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

fun getDateTime() : String {
    val now = Date()
    val calendar = Calendar.getInstance()
    calendar.time = now

    val month = calendar.get(Calendar.MONTH)+1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    return "[${month}-${day} ${hour}:${minute}]"
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

    compileOptions {
        sourceCompatibility = AppConfig.javaVersion
        targetCompatibility = AppConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }

    buildFeatures {
        compose = true
        // 기존 BuildConfig 는 사용 되지 않음
        buildConfig = false
    }

    buildTypes {

        getByName("debug") {
            isMinifyEnabled = false
            versionNameSuffix = "Debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig = signingConfigs.getByName("key")
        }
    }

    // https://developer.android.com/studio/build?hl=ko
    // https://github.com/mustafayigitt/KotlinDSL-BuildSrcExample/blob/master/app/build.gradle.kts
    flavorDimensions.add("appType")
    productFlavors {
        create("dev") {
            dimension = "appType"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev-${getDateTime()}"
            manifestPlaceholders["applicationLabel"] = "@string/app_name_dev"
        }
        create("prod") {
            dimension = "appType"
            manifestPlaceholders["applicationLabel"] = "@string/app_name_prod"
        }
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