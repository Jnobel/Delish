plugins {
    id("com.android.library") // ✅ UI-related modules are libraries
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "app.delish.bookmark"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
            "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
            "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    api(project(":domain"))
    implementation(project(":base"))
    implementation(project(":common:compose"))
    api(project(":common:imageloading"))

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.compose.foundation.foundation)
    implementation(libs.compose.foundation.layout)
    implementation(libs.compose.material.material)
    implementation(libs.compose.animation.animation)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.constraint.layout)
    implementation(libs.compose.material.iconsext)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    implementation(libs.hilt.compose)
    implementation(libs.hilt.library)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android.v248)
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.15")
}