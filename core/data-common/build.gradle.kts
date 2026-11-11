plugins {
    alias(libs.plugins.clipstack.kotlin.jvm)
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.coroutines.core)
}
