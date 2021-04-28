plugins {
    kotlin("js") version "1.5.0"
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
}

dependencies {
    implementation("org.jetbrains:kotlin-react:17.0.2-pre.153-kotlin-1.4.32")
    implementation("org.jetbrains:kotlin-react-dom:17.0.2-pre.153-kotlin-1.4.32")
    implementation("org.jetbrains:kotlin-react-table:7.6.3-pre.153-kotlin-1.4.32")
    implementation("org.jetbrains:kotlin-styled:5.2.3-pre.153-kotlin-1.4.32")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
}

kotlin.js {
    browser()
    binaries.executable()
}

tasks.wrapper {
    gradleVersion = "7.0"
    distributionType = Wrapper.DistributionType.ALL
}
