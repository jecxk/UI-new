pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    // Khai báo version cho Android Gradle Plugin (AGP)
    plugins {
        id("com.android.application") version "8.5.2"
        id("com.android.library") version "8.5.2"
        // Nếu dự án KHÔNG dùng Kotlin, không cần thêm Kotlin plugin ở đây
    }
}

dependencyResolutionManagement {
    // Đang dùng repo ở settings -> không được khai báo repo ở build.gradle nữa
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "IRC"
include(":app")
