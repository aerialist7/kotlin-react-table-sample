import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

plugins {
    kotlin("multiplatform")
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

kotlin {
    js {
        browser {
            commonWebpackConfig {
                outputFileName = "index.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(libs.wrappers.react)
                implementation(libs.wrappers.reactDom)
                implementation(libs.wrappers.emotion)
                implementation(libs.wrappers.tanstack.reactQuery)
                implementation(libs.wrappers.tanstack.reactTable)
            }
        }
    }
}

tasks.withType<KotlinJsCompile>().configureEach {
    compilerOptions {
        target.set("es2015")
    }
}

tasks.wrapper {
    gradleVersion = "8.10"
}
