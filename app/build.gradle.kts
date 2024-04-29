plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}
buildscript {
    // Otros bloques y configuraciones...
    dependencies {
        // Otras dependencias...
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
    }
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
    buildFeatures {
        viewBinding = true
    }

    android {
        packagingOptions {
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
        }
    }

}

dependencies {

    // Dependencias de producción
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
    implementation(libs.room.runtime)
    implementation(libs.picasso)
    implementation("org.apache.httpcomponents:httpclient-android:4.3.5.1")
    implementation("com.android.volley:volley:1.1.1")


    implementation("androidx.fragment:fragment-ktx:1.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation(libs.core.ktx)
    implementation(libs.androidx.junit.ktx)

    // Dependencias de prueba
    testImplementation(libs.junit)
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3")
    testImplementation("org.mockito:mockito-core:3.12.4")



    /* androidTestImplementation(libs.androidx.junit)
    androidTestImplementation("org.mockito:mockito-core:3.11.2")
    androidTestImplementation("org.mockito:mockito-android:3.11.2")
    androidTestImplementation("org.mockito:mockito-inline:3.11.2")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation(libs.androidx.espresso.core) // Agregar Espresso
    */
}