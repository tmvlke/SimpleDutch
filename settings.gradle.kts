pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SimpleDutch"
include(":domain")
include(":data")
include(":presentation")
include(":presentation:baseUiKit")
include(":presentation:homeTab")
include(":presentation:storageTab")
