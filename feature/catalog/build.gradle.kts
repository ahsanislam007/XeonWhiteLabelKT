plugins {
    alias(libs.plugins.clipstack.android.library)
    alias(libs.plugins.clipstack.compose)
}

android {
    namespace = "dev.clipstack.feature.catalog"
    buildFeatures { compose = true }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.analytics)
    implementation(projects.ui.common)

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.coroutines.android)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.foundation)
}
