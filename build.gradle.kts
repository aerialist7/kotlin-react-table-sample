plugins {
    kotlin("js") version "1.5.0"
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
}

fun kotlinw(target: String): String =
    "org.jetbrains:kotlin-$target-pre.154-kotlin-1.4.32"

dependencies {
    implementation(kotlinw("react:17.0.2"))
    implementation(kotlinw("react-dom:17.0.2"))
    implementation(kotlinw("react-table:7.7.0"))
    implementation(kotlinw("styled:5.2.3"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks.wrapper {
    gradleVersion = "7.0.2"
    distributionType = Wrapper.DistributionType.ALL
}
