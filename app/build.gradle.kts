plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.saksh_cipher"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.saksh_cipher"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)

    implementation(libs.recyclerview) // Latest version of RecyclerView
    implementation(libs.cardview)
    implementation(libs.constraintlayout.v220)
    implementation(libs.appcompat)

    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}