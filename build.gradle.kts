plugins {
    id("java")
    id("com.gradleup.shadow") version "9.6.1"
}

group = "nl.bioinf.MuSeq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.7")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

// https://gradleup.com/shadow/configuration/#configuring-the-jar-manifest
tasks.jar {
    manifest {
        attributes["Main-Class"] = "MuSeq.Main"
    }
}