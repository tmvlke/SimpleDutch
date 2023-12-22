import org.gradle.api.JavaVersion

object AppConfig {

    const val versionCode = 1
    const val versionName = "1.0.0"

    const val sdkMin = 23
    const val targetSdk = 34
    const val buildToolsVersion = "34.0.0"

    const val jvmTarget = "17"
    val javaVersion = JavaVersion.VERSION_17

    const val sdkPreview = "UpsideDownCake"

    const val kotlin = "1.8.10"

    // https://developer.android.com/jetpack/androidx/releases/compose-kotlin?hl=ko
    const val kotlinCompilerExtensionVersion = "1.4.3" // 1.8.10
}