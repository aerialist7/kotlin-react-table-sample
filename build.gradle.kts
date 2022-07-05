plugins {
    kotlin("js")
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.351")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("emotion"))
    implementation(kotlinw("react-query"))
    implementation(kotlinw("react-table"))
}

kotlin.js {
    browser()
    binaries.executable()
}

// TODO: Remove when will be fixed in newest version of Kotlin
extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.10.0"
}

tasks.wrapper {
    gradleVersion = "7.4.2"
}
