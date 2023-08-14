plugins {
    kotlin("js")
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

val wrappersVersion = extra["kotlin.wrappers.version"]

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:$wrappersVersion")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("emotion"))
    implementation(kotlinw("tanstack-react-query"))
    implementation(kotlinw("tanstack-react-table"))
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks.wrapper {
    gradleVersion = "8.2.1"
}
