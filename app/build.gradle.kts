plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mercadolibre.melitest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mercadolibre.melitest"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    /*** Core ***/
    implementation(libs.androidx.core.ktx)

    /*** Compose ***/
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    /*** Compose Navigation ***/
    implementation(libs.compose.navigation)

    /*** lifecycle ***/
    implementation(libs.androidx.lifecycle.runtime.ktx)

    /*** DI ***/
    implementation(libs.koin)

    /*** Serialization ***/
    implementation(libs.kotlinx.serialization)

    /*** Network ***/
    implementation(libs.bundles.network)

    /*** Image ***/
    implementation(libs.bundles.image)

    /*** Testing ***/
    testImplementation(libs.junit)
    testImplementation(libs.mockK)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}