// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${AppConfig.kotlin}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")

        // test
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")
    }
}

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version(AppConfig.kotlin) apply false
}