plugins {
    kotlin("js") version "1.5.31"
}

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    implementation(enforcedPlatform(kotlinw("wrappers-bom:0.0.1-pre.256-kotlin-1.5.31")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("react-css"))
    implementation(kotlinw("react-table"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}

kotlin.js {
    browser()
    binaries.executable()

    // TODO: Temporary until Kotlin update
    rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin::class.java) {
        rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().versions.webpackCli.version = "4.9.0"
    }
}

tasks.wrapper {
    gradleVersion = "7.2"
    distributionType = Wrapper.DistributionType.ALL
}
