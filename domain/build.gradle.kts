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

    // ✅ Ensure namespace is correct
    namespace = "app.delish.domain"
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // ✅ Paging Dependencies
    implementation("androidx.paging:paging-runtime:3.2.0")
    implementation("androidx.paging:paging-common:3.2.0")

    // ✅ Ensure Hilt dependencies are present
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // ✅ Ensure Coroutines dependencies are present
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") // Required for UI threading

    // ✅ Ensure Inject annotations work correctly
    implementation("javax.inject:javax.inject:1")

    // ✅ DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // ✅ KSP Dependencies
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.15")
}