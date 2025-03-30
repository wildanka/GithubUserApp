plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose) // compose
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization) apply false
}

android {
    namespace = "com.example.hcsgithubuser"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hcsgithubuser"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        val githubToken: String = project.findProperty("GITHUB_TOKEN") as String? ?: ""
        val githubApiVersion: String = project.findProperty("GITHUB_API_VERSION") as String? ?: ""
        buildConfigField("String", "GITHUB_TOKEN", "\"$githubToken\"")
        buildConfigField("String", "GITHUB_API_VERSION", "\"$githubApiVersion\"")

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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":arch"))
    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp)
    implementation(libs.androidx.paging.runtime.ktx)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.androidx.swiperefreshlayout)

    //chucker
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)
    // Koin for Android
    implementation(libs.bundles.koin)
    // Koin Annotations KSP Compiler
    ksp(libs.koin.ksp.compiler)

    //Room
    implementation(libs.bundles.room)
    ksp(libs.room.ksp)

    //kotlin serialization Json
    implementation(libs.serialization.json)

    //region compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.bundles.koin.compose)
    implementation(libs.bundles.coil)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //endregion compose

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}