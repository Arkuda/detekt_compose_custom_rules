import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    detektPlugins(project(":customcomposerules", "default"))
}

tasks.withType<Detekt>(){
    buildUponDefaultConfig = true
    basePath = rootDir.toString()
    config.setFrom("$rootDir/config/detekt/detekt.yml")
    setSource(files(rootDir))
    include("**/*.kt")
    exclude("**/build/**")
    jvmTarget = "17"

    dependsOn(":customcomposerules:build")
}


tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with GitHub Code Scanning
        md.required.set(true) // simple Markdown format
    }
}

// Kotlin DSL
tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}