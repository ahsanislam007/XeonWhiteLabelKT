pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "ClipStack"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":core:domain")
include(":core:database")
include(":core:data-common")
include(":core:data-rest")
include(":core:data-firebase")
include(":core:analytics")
include(":ui:common")
include(":feature:catalog")
include(":brands:apex")
include(":brands:bloom")
