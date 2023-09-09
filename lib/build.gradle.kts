import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

plugins {
    `java-library`
}

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = URI.create("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = URI.create("https://oss.sonatype.org/content/groups/public/")
    }
}

group = "red.oases"
version = SimpleDateFormat("YYYY-MM-dd_HH.mm.ss").format(Date())

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:4.14.0")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("com.mysql:mysql-connector-j:8.1.0")
    implementation(files("src/main/java/red/oases/core/Extra/Lib/commons-dbutils-1.8.0.jar"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}