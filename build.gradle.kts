plugins {
    kotlin("js")
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.380")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("emotion"))
    implementation(kotlinw("react-query"))
    implementation(kotlinw("tanstack-react-table"))
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks.wrapper {
    gradleVersion = "7.5"
}
