plugins {
    alias(libs.plugins.clipstack.android.application)
    alias(libs.plugins.clipstack.compose)
}

android {
    namespace = "dev.clipstack.bloom"
    defaultConfig {
        applicationId = "dev.clipstack.bloom"
        versionCode = 1
        versionName = "0.1.0"
    }
    buildFeatures { compose = true }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.dataCommon)
    implementation(projects.core.dataFirebase)
    implementation(projects.core.analytics)
    implementation(projects.feature.catalog)
    implementation(projects.ui.common)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
}
