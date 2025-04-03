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
    buildUponDefaultConfig = true // ✅ Preconfigure defaults
    allRules = true // ✅ Activate all available (even unstable) rules.
    config = files("$rootDir/detekt.yml") // ✅ Point to your custom config
}

android {
    namespace = "app.delish.common.resources"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

dependencies {
    // ✅ Placeholder for future dependencies if needed
}