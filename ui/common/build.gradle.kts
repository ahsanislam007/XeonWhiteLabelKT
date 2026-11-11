plugins {
    alias(libs.plugins.clipstack.android.library)
    alias(libs.plugins.clipstack.compose)
}

android {
    namespace = "dev.clipstack.ui.common"
    buildFeatures { compose = true }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
}
