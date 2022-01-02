plugins {
    kotlin("js") version "1.6.10"
}

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:0.0.1-pre.285-kotlin-1.6.10")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("react-css"))
    implementation(kotlinw("react-table"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks.wrapper {
    gradleVersion = "7.3.3"
}
