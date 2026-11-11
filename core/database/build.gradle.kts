plugins {
    alias(libs.plugins.clipstack.android.library)
    alias(libs.plugins.sqldelight)
}

android {
    namespace = "dev.clipstack.database"
}

sqldelight {
    databases {
        create("ClipStackDatabase") {
            packageName.set("dev.clipstack.database")
        }
    }
}

dependencies {
    implementation(libs.sqldelight.android)
    implementation(libs.sqldelight.coroutines)
    implementation(libs.coroutines.core)
}
