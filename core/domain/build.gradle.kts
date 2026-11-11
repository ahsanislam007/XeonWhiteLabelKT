plugins {
    alias(libs.plugins.clipstack.kotlin.jvm)
}

dependencies {
    implementation(libs.coroutines.core)
    testImplementation(libs.junit)
}
