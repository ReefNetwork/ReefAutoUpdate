plugins {
    java
    maven
    kotlin("jvm") version "1.3.71"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("net.minecrell.plugin-yml.nukkit") version "0.3.0"

}

group = "net.ree_jp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(
        url = uri("https://repo.nukkitx.com/main/")
    )
    maven(
        url = uri("https://www.jitpack.io")
    )
}

dependencies {
    compileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    testCompileOnly("cn.nukkit:nukkit:1.0-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("net.lingala.zip4j:zip4j:2.5.2")
    implementation("com.github.Ree-jp:GIthub-artifacts-url:1.0.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

nukkit {
    name = "ReefAutoUpdate"
    main = "net.ree_jp.reefUpdate.ReefAutoUpdatePlugin"
    api = listOf("1.0.0")
    author = "Ree-jp"
    description = "ReefAutoUpdatePlugin"
    website = "https://github.com/ReefNetwork/"
    version = "1.0.1"
}
