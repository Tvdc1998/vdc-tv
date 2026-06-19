plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
<<<<<<< HEAD
    namespace = "com.vdc.tv.data"
=======
    namespace = "dev.jdtech.jellyfin.data"
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
    compileSdk = Versions.COMPILE_SDK
    buildToolsVersion = Versions.BUILD_TOOLS

    defaultConfig {
        minSdk = Versions.MIN_SDK

        buildConfigField("int", "VERSION_CODE", Versions.APP_CODE.toString())
        buildConfigField("String", "VERSION_NAME", "\"${Versions.APP_NAME}\"")

        consumerProguardFile("proguard-rules.pro")

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.generateKotlin", "true")
        }
    }

    buildTypes {
        named("release") { isMinifyEnabled = false }
        register("staging") { initWith(getByName("release")) }
    }

    compileOptions {
        sourceCompatibility = Versions.JAVA
        targetCompatibility = Versions.JAVA
    }

    buildFeatures { buildConfig = true }
}

dependencies {
    implementation(projects.settings)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.jellyfin.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation(libs.timber)
}
