import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library") // ✅ For library modules
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp")
}

detekt {
    // Your Detekt configuration goes here
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = true // activate all available (even unstable) rules.
    config = files("$rootDir/detekt.yml") // point to your custom config
}

android {
    namespace = "app.delish.base" // ✅ Confirm correct package name

    compileSdk = 34  // ✅ Added compileSdkVersion

    defaultConfig {
        minSdk = 23
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17  // ✅ Updated for Gradle 8.2+ compatibility
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"  // ✅ Updated to match Java 17
    }
}

dependencies {
    // ✅ REMOVE `api(projects.domain)` to break circular dependency

    api(libs.coroutines.core)
    api(libs.dagger.dagger)
    api(libs.lifecycle.viewmodel.ktx)
    implementation(libs.hilt.android.v248)
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.13")

    // ✅ Added missing Hilt Compiler dependency
    kapt(libs.hilt.compiler)


    // ✅ Required Compose dependencies
    api("androidx.compose.material:material:1.4.0")
    api("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")
    api("com.google.accompanist:accompanist-navigation-material:0.27.0")
    api("com.google.accompanist:accompanist-pager:0.27.0")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview"
        )
        jvmTarget = "17"  // ✅ Updated to match Java 17
    }
}