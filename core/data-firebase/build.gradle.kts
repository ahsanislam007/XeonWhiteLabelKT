plugins {
    alias(libs.plugins.clipstack.android.library)
}

android {
    namespace = "dev.clipstack.data.firebase"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.dataCommon)
    implementation(libs.coroutines.core)
}
