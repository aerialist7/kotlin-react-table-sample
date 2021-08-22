plugins {
    kotlin("js") version "1.5.21"
}

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:0.0.1-pre.233-kotlin-1.5.21")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("react-table"))
    implementation(kotlinw("styled"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks.wrapper {
    gradleVersion = "7.0.2"
    distributionType = Wrapper.DistributionType.ALL
}
