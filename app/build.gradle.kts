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
    buildFeatures {
        viewBinding = true
    }

    android {
        packagingOptions {
            resources.excludes.add("mockito-extensions/org.mockito.plugins.MockMaker")
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

    implementation("androidx.fragment:fragment-ktx:1.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("com.github.bumptech.glide:glide:4.12.0")
    // Ktor dependencies
    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-android:2.0.0")
    implementation("io.ktor:ktor-client-logging:2.0.0")
    implementation("io.ktor:ktor-client-json:2.0.0")
    implementation("io.ktor:ktor-client-serialization:2.0.0")


    // Dependencias de prueba
    testImplementation(libs.junit)
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("androidx.test:core:1.4.0")
    testImplementation("org.mockito:mockito-android:3.11.2")
    testImplementation("org.mockito:mockito-inline:3.11.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation(libs.androidx.espresso.core) // Agregar Espresso

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation("org.mockito:mockito-android:3.11.2")
    androidTestImplementation("org.mockito:mockito-inline:3.11.2")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation(libs.androidx.espresso.core) // Agregar Espresso
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")

}