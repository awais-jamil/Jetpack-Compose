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

rootProject.name = "jetpack-project"
include(":app")
include(":features")
include(":ui")
include(":common:ui")
include(":common:api_service")
include(":common:firebase_service")
