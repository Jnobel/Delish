plugins {
    id("com.android.library") // ✅ UI-related modules are libraries
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlin.Experimental",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
        )
    }
    namespace = "app.delish.common.imageloading"
}
dependencies {
    implementation(project(":domain"))  // ✅ Corrected domain reference
    implementation(project(":base"))  // ✅ Ensure base module is accessible
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.13")

    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.android.v248)
    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)

    api(libs.coil.coil)
}