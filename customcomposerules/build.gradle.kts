plugins {
    id("kotlin")
}

group = "ru.kiryantsev.customcomposerules"
version = "1.0-SNAPSHOT"


dependencies {
    implementation(libs.kotlin.stdlib)

    compileOnly(libs.detekt.api)
}
