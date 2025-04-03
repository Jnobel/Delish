plugins {
    id("com.android.application") // ✅ For the main app module
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

val appVersionCode = propOrDef("Delish_VERSIONCODE", "1").toInt()

android {
    compileSdk = 34

    namespace = "com.elbehiry.delish"

    defaultConfig {
        applicationId = "com.elbehiry.delish"
        minSdk = 23
        targetSdk = 34
        versionCode = appVersionCode
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "SPOONACULAR_KEY", "\"${System.getenv("SPOONACULAR_API_KEY") ?: project.findProperty("SPOONACULAR_API_KEY") ?: ""}\"")
        buildConfigField("String", "SPOONACULAR_BASE_URL", "\"https://api.spoonacular.com/\"")
        buildConfigField("String", "CUISINES_DATA_URL", "\"https://firebasestorage.googleapis.com/v0/b/delish-d4e2b.appspot.com/o/getCuisines.json?alt=media\"")
        buildConfigField("String", "INGREDIENTS_DATA_URL", "\"https://firebasestorage.googleapis.com/v0/b/delish-d4e2b.appspot.com/o/ingredients.json?alt=media\"")

        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
                arguments["room.incremental"] = "true"
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // ✅ Hardcoded stable Compose Compiler version
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/licenses/**")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation("com.google.android.material:material:1.9.0") // ✅ Ensure Material Components are available

    // ✅ Hilt Dependencies
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // ✅ DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // ✅ Hilt ViewModel extension
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

    // ✅ Hilt WorkManager integration (if used)
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // ✅ Ensure Kotlin Annotation Processor is enabled
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    // ✅ Ensure Kotlin Standard Library
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.21")) // 🔥 **Updated to latest Kotlin BOM**
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")

    // ✅ Ensure KSP compatibility
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.15")

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib") {
            version {
                strictly("1.9.21") // 🔥 **Ensures consistency with Kotlin BOM**
            }
        }
    }
}

// ✅ Utility function for reading properties safely
fun <T : Any> propOrDef(propertyName: String, defaultValue: T): T {
    @Suppress("UNCHECKED_CAST")
    val propertyValue = project.properties[propertyName] as T?
    return propertyValue ?: defaultValue
}