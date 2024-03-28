import kotlinx.benchmark.gradle.JvmBenchmarkTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

plugins {
    kotlin("multiplatform") version "1.9.22"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { nodejs() }
    js(IR) { nodejs() }

    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.10")
            }
        }
    }
}

benchmark {
    targets {
        register("js")
        register("wasmJs")
    }

    configurations {
        create("AllThree_BH") {
            include("org.example.BenchmarkWithBlackhole.[abc]")
        }
        create("LastTwo_BH") {
            include("org.example.BenchmarkWithBlackhole.[bc]")
        }
        create("LastOne_BH") {
            include("org.example.BenchmarkWithBlackhole.c")
        }

        create("AllThree_WBH") {
            include("org.example.BenchmarkWithoutBlackhole.[abc]")
        }
        create("LastTwo_WBH") {
            include("org.example.BenchmarkWithoutBlackhole.[bc]")
        }
        create("LastOne_WBH") {
            include("org.example.BenchmarkWithoutBlackhole.c")
        }
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "21.0.0-v8-canary202310177990572111"
    nodeDownloadBaseUrl = "https://nodejs.org/download/v8-canary"
}

rootProject.tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}
