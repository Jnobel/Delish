plugins {
    id("com.android.library") // ✅ For library modules
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp")
}

detekt {
    buildUponDefaultConfig = true
    allRules = true
    config = files("$rootDir/detekt.yml")
}

dependencies {
    implementation(libs.moshi)
    implementation(libs.hilt.android.v248)
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.15")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}