pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

}
rootProject.name = "Modo"

include(":modo")
include(":modo-render-android-fm")
include(":modo-render-android-compose")

include(":sample-android-fm")
include(":sample-android-compose")
