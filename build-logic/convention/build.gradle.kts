plugins {
    `kotlin-dsl`
}

group = "dev.clipstack.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "dev.clipstack.android.application"
            implementationClass = "dev.clipstack.convention.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "dev.clipstack.android.library"
            implementationClass = "dev.clipstack.convention.AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = "dev.clipstack.compose"
            implementationClass = "dev.clipstack.convention.ComposeConventionPlugin"
        }
        register("kotlinJvm") {
            id = "dev.clipstack.kotlin.jvm"
            implementationClass = "dev.clipstack.convention.KotlinJvmConventionPlugin"
        }
    }
}
