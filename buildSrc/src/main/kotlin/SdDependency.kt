object SdDependency {

    const val coil = "io.coil-kt:coil-compose:2.5.0"
    const val gson = "com.google.code.gson:gson:2.10.1"
    const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:0.33.1-alpha"

    object Androidx {
        const val core = "androidx.core:core-ktx:1.12.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2"
        const val activity = "androidx.activity:activity-compose:1.8.2"

        const val splashscreen = "androidx.core:core-splashscreen:1.0.1"
        const val constraintlayoutCompose = "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13"
        object Compose {
            object Bom {
                const val bom = "androidx.compose:compose-bom:2023.03.00"
                const val ui = "androidx.compose.ui:ui"
                const val uiGraphics = "androidx.compose.ui:ui-graphics"
                const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
                const val material3 = "androidx.compose.material3:material3"
            }

            const val navigationCompose = "androidx.navigation:navigation-compose:2.7.6"
            const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.1.0"
        }
    }


    object Hilt {
        const val android = "com.google.dagger:hilt-android:2.48.1"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:2.48.1"
    }
}