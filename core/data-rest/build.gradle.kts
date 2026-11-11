plugins {
    alias(libs.plugins.clipstack.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dev.clipstack.data.rest"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(libs.coroutines.core)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)
}
