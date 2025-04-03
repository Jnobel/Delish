import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.github.ben-manes.versions") version "0.48.0" apply true
    id("io.gitlab.arturbosch.detekt") version "1.23.1" apply true
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1" apply true
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false // ✅ Add KSP Plugin
    kotlin("jvm") version "1.9.0"
}

// ✅ Ensure `detekt` is correctly configured
detekt {
    toolVersion = "1.23.1"
    config = files("${rootProject.projectDir}/config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

// ✅ Manually configure repositories
allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// ✅ Fix the `clean` task issue
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// ✅ Configure all subprojects
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

}

// ✅ Apply Kotlin settings globally for all Kotlin-based subprojects
plugins.withId("org.jetbrains.kotlin.jvm") {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = false
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
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}

// ✅ Ensure Hilt settings are properly applied
plugins.withId("com.google.dagger.hilt.android") {
    extensions.getByType<dagger.hilt.android.plugin.HiltExtension>().enableAggregatingTask = true
}

// ✅ Ensure kapt processes Hilt correctly
plugins.withId("org.jetbrains.kotlin.kapt") {
    extensions.getByType<org.jetbrains.kotlin.gradle.plugin.KaptExtension>().correctErrorTypes = true
}

// ✅ Configure Android settings for subprojects that use Android Gradle Plugin
plugins.withType<com.android.build.gradle.BasePlugin>().configureEach {
    extensions.configure<com.android.build.gradle.LibraryExtension> {
        compileSdk = 34

        defaultConfig {
            minSdk = 24
            targetSdk = 34
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}
