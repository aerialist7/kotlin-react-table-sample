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
                implementation(libs.wrappers.react.dom)
                implementation(libs.wrappers.emotion)
                implementation(libs.wrappers.tanstack.react.query)
                implementation(libs.wrappers.tanstack.react.table)
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
