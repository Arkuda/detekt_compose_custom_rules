plugins {
    id(libs.plugins.android.lib.get().pluginId)
    alias(libs.plugins.jetbrains.kotlin.android)
}




android {
    namespace = "ru.kiryantsev.customcomposerules"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.core.ktx)
    compileOnly(libs.detekt.api)

    implementation(libs.kotlin.stdlib)
    implementation(libs.detekt.cli)

}