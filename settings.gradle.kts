enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "VdC_Studios"

include(":app:phone")
include(":app:tv")
include(":core")
include(":data")
include(":player:core")
include(":player:local")
include(":setup")
include(":modes:film")
include(":settings")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8
