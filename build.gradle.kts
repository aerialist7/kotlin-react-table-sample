plugins {
    kotlin("js") version "1.4.21"
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains:kotlin-react:17.0.1-pre.137-kotlin-1.4.21")
    implementation("org.jetbrains:kotlin-react-dom:17.0.1-pre.137-kotlin-1.4.21")
    implementation("org.jetbrains:kotlin-react-table:7.6.3-pre.137-kotlin-1.4.21")
    implementation("org.jetbrains:kotlin-styled:5.2.0-pre.137-kotlin-1.4.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}

kotlin.js {
    browser {
        binaries.executable()
    }
}

tasks.wrapper {
    gradleVersion = "6.8"
    distributionType = Wrapper.DistributionType.ALL
}
