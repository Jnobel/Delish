pluginManagement {
    repositories {
        gradlePluginPortal() // ✅ Required for Gradle plugins
        google() // ✅ Required for Android dependencies
        mavenCentral() // ✅ Required for KSP
        maven { url = uri("https://plugins.gradle.org/m2/") } // ✅ Ensures plugin resolution
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))  // Load versions from TOML
        }
    }
}

// ✅ Move `rootProject.name` OUTSIDE the `dependencyResolutionManagement` block
rootProject.name = "Delish"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":mobile",
    ":data",
    ":model",
    ":base",
    ":domain",
    ":common:imageloading",
    ":common:compose",
    ":common:resources",
    ":ui:onboarding",
    ":ui:discover",
    ":ui:search",
    ":ui:bookmark",
    ":ui:details",
    ":ui:settings"
)
