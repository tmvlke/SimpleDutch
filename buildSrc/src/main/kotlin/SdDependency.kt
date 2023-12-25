object SdDependency {

    object Coroutines {
        // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"
        // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-android
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
    }

    object Ktx {
        // https://mvnrepository.com/artifact/androidx.core/core-ktx
        const val core = "androidx.core:core-ktx:1.12.0-rc01"
        // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-runtime-ktx
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0-alpha01"
    }

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    const val gson = "com.google.code.gson:gson:2.10.1"
    const val coil = "io.coil-kt:coil-compose:2.5.0"
    // https://androidx.tech/artifacts/core/core-splashscreen/
    const val defaultSplash = "androidx.core:core-splashscreen:1.1.0-alpha02"

    object Hilt {
        // https://mvnrepository.com/artifact/com.google.dagger/hilt-android
        const val android = "com.google.dagger:hilt-android:2.48.1"
        // https://mvnrepository.com/artifact/com.google.dagger/hilt-android-compiler
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:2.48.1"
    }

    object Compose {
        const val activity = "androidx.activity:activity-compose:1.8.2"
        const val bom = "androidx.compose:compose-bom:2023.10.01"
        const val ui = "androidx.compose.ui:ui"
        const val graphics = "androidx.compose.ui:ui-graphics"
        const val preview = "androidx.compose.ui:ui-tooling-preview"
        const val material3 = "androidx.compose.material3:material3"

        // https://mvnrepository.com/artifact/com.google.android.material/material
        // https://mvnrepository.com/artifact/androidx.constraintlayout/constraintlayout-compose
        const val constraint = "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha11"
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:2.7.6"
        const val hilt = "androidx.hilt:hilt-navigation-compose:1.1.0"
        // https://mvnrepository.com/artifact/com.google.accompanist/accompanist-navigation-animation
        const val animation = "com.google.accompanist:accompanist-navigation-animation:0.33.1-alpha"
    }

    object Repository {
        // https://mvnrepository.com/artifact/androidx.preference/preference-ktx
        const val preference = "androidx.preference:preference-ktx:1.2.1"
    }
}