plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
}

android {
<<<<<<< HEAD
    namespace = "com.vdc.tv.player.core"
=======
    namespace = "dev.jdtech.jellyfin.player.core"
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
    compileSdk = Versions.COMPILE_SDK
    buildToolsVersion = Versions.BUILD_TOOLS

    defaultConfig { minSdk = Versions.MIN_SDK }

    buildTypes {
        named("release") { isMinifyEnabled = false }
        register("staging") { initWith(getByName("release")) }
    }

    compileOptions {
        sourceCompatibility = Versions.JAVA
        targetCompatibility = Versions.JAVA
    }
}

dependencies { implementation(libs.timber) }
