import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(notation = libs.plugins.kotlinMultiplatform)
    alias(notation = libs.plugins.androidApplication)
    alias(notation = libs.plugins.jetbrainsCompose)
    alias(notation = libs.plugins.compose.compiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm(name = "desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(dependencyNotation = compose.preview)
            implementation(dependencyNotation = libs.androidx.activity.compose)
            implementation(dependencyNotation = libs.material3)
            implementation(dependencyNotation = projects.shared)
            implementation(dependencyNotation = libs.koin.android)
            implementation(dependencyNotation = libs.koin.core)
            implementation(dependencyNotation = libs.koin.androidx.compose)
            implementation(dependencyNotation = "com.arthenica:ffmpeg-kit-full:6.0-2")
        }
        commonMain.dependencies {
            implementation(dependencyNotation = compose.runtime)
            implementation(dependencyNotation = compose.foundation)
            implementation(dependencyNotation = compose.ui)
            implementation(dependencyNotation = compose.components.resources)
            implementation(dependencyNotation = compose.components.uiToolingPreview)
            // implementation(dependencyNotation = libs.androidx.lifecycle.viewmodel)
            implementation(dependencyNotation = libs.androidx.lifecycle.runtime.compose)
            implementation(dependencyNotation = projects.shared)
            implementation(dependencyNotation = compose.material3)
            implementation(dependencyNotation = libs.voyager.navigator)
            implementation(dependencyNotation = libs.voyager.tabNavigator)
            implementation(dependencyNotation = libs.voyager.transitions)
            api(dependencyNotation = libs.koin.core)
            implementation(dependencyNotation = libs.koin.compose)
            implementation(dependencyNotation = libs.koin.compose.viewmodel)
            implementation(dependencyNotation = libs.lifecycle.viewmodel)
        }
        commonTest.dependencies {
            implementation(dependencyNotation = libs.kotlin.test)
            implementation(
                dependencyNotation = kotlin(simpleModuleName = "test-annotations-common")
            )
            implementation(dependencyNotation = libs.assertK)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(dependencyNotation = compose.uiTest)
        }
        desktopMain.dependencies {
            implementation(dependencyNotation = compose.desktop.currentOs)
            implementation(dependencyNotation = libs.kotlinx.coroutines.swing)
        }
    }
}

android {
    namespace = "emmanuelmuturia.sonux"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "emmanuelmuturia.sonux"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared"))
    debugImplementation(dependencyNotation = compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "emmanuelmuturia.sonux.MainKt"

        nativeDistributions {
            targetFormats(formats = arrayOf(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb))
            packageName = "emmanuelmuturia.sonux"
            packageVersion = "1.0.0"
        }
    }
}