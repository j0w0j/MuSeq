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
tasks.shadowJar { // voor in de jar
    manifest {
        attributes["Main-Class"] = "MuSeq.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}