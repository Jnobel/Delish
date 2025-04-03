plugins {
    id("com.android.library") // ✅ For library modules
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.devtools.ksp") // ✅ Ensure KSP plugin is applied
}

detekt {
    buildUponDefaultConfig = true // ✅ Preconfigure defaults
    allRules = true // ✅ Activate all available (even unstable) rules.
    config = files("$rootDir/detekt.yml") // ✅ Point to your custom config
}

android {
    compileSdk = 34  // ✅ Ensure correct SDK version

    defaultConfig {
        minSdk = 23
        targetSdk = 34
    }

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

    namespace = "app.delish.data"
}

kapt {
    correctErrorTypes = true
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
}

dependencies {
    implementation(project(":domain"))  // ✅ Fixed reference
    implementation(project(":model"))  // ✅ Fixed reference

    // ✅ Ensure necessary dependencies
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.paging.runtime)

    implementation(libs.timber)

    // ✅ Networking
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)
    testImplementation(libs.okhttp.mockwebserver)

    // ✅ Retrofit & Moshi
    implementation(libs.retrofit)
    implementation(libs.moshi)
    implementation(libs.retrofit.converter.moshi)

    // ✅ Coroutines
    implementation(libs.coroutines.core)

    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android.v248)

    // ✅ Data store
    implementation(libs.datastore)

    // ✅ Ensure Gson dependency is available
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ✅ Ensure Retrofit dependencies are present
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // ✅ Ensure Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // ✅ Ensure Android Lifecycle & ViewModel Dependencies
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // ✅ Ensure Accompanist and Compose dependencies
    implementation("com.google.accompanist:accompanist-navigation-material:0.31.2-alpha")
    implementation("com.google.accompanist:accompanist-pager:0.31.2-alpha")

    ksp("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.15")
}