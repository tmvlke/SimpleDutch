object SdTestDependency {
    object Tdd {
        // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
        const val junit5 = "org.junit.jupiter:junit-jupiter-api:5.10.0"
        // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
        const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:5.10.0"
        // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params
        const val junit5Params = "org.junit.jupiter:junit-jupiter-params:5.10.0"
        // https://mvnrepository.com/artifact/org.junit.vintage/junit-vintage-engine
        const val junit5ParamsEngine = "org.junit.vintage:junit-vintage-engine:5.10.0"
        // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-test
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3"
        // https://mvnrepository.com/artifact/io.mockk/mockk
        const val mockk = "io.mockk:mockk:1.13.7"
    }


    object Bdd {
        // https://mvnrepository.com/artifact/androidx.test.ext/junit
        const val junit = "androidx.test.ext:junit:1.1.5"

        // https://mvnrepository.com/artifact/androidx.compose/compose-bom
        const val composeBom = "androidx.compose:compose-bom:2023.10.00"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4"


        const val debugComposeTooling = "androidx.compose.ui:ui-tooling"
        const val debugComposeManifest = "androidx.compose.ui:ui-test-manifest"
    }
}