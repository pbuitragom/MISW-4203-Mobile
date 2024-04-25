plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}

android {
    namespace = "co.com.uniandes.vinilos"
    compileSdk = 34

    buildFeatures{
        dataBinding = true
    }

    defaultConfig {
        applicationId = "co.com.uniandes.vinilos"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.volley)
    implementation(libs.retrofit2)
    implementation(libs.gson)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.converter.scalars)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.room.runtime)
    // Dependencia para el uso de viewModels
    implementation("androidx.fragment:fragment-ktx:1.3.1") // Asegúrate de usar la versión más reciente

    // Dependencias del ViewModel y LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1") // Asegúrate de usar la versión más reciente
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1") // Asegúrate de usar la versión más reciente
    // Si estás usando Java, también necesitarás la dependencia lifecycle-extensions, que está deprecada en Kotlin
    // implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // Otras dependencias del lifecycle que puedas necesitar
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

}