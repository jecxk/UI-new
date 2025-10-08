plugins {
    id("com.android.application")   // version đã khai báo ở settings.gradle.kts
}

android {
    namespace = "vn.edu.usth.irc"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.edu.usth.irc"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.activity:activity:1.9.2")
    implementation("androidx.fragment:fragment:1.8.3")
    implementation("de.hdodenhof:circleimageview:3.1.0")
}
