plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

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


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}