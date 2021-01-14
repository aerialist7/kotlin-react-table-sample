plugins {
    kotlin("js") version "1.4.21"
}

group = "com.github.aerialist7"
version = "1.0.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
}

dependencies {
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains:kotlin-react:17.0.1-pre.136-kotlin-1.4.10")
    implementation("org.jetbrains:kotlin-react-dom:17.0.1-pre.136-kotlin-1.4.10")
    implementation("org.jetbrains:kotlin-react-table:7.6.2-pre.136-kotlin-1.4.10")
    implementation("org.jetbrains:kotlin-styled:5.2.0-pre.136-kotlin-1.4.10")
}

kotlin {
    js(LEGACY) {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}

tasks.wrapper {
    gradleVersion = "6.8"
    distributionType = Wrapper.DistributionType.ALL
}
