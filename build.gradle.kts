plugins {
    kotlin("js") version "1.5.0"
}

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target-pre.204-kotlin-1.5.0"

dependencies {
    implementation(kotlinw("react:17.0.2"))
    implementation(kotlinw("react-dom:17.0.2"))
    implementation(kotlinw("react-table:7.7.0"))
    implementation(kotlinw("styled:5.3.0"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks.wrapper {
    gradleVersion = "7.0.2"
    distributionType = Wrapper.DistributionType.ALL
}
