plugins {
    id("java")
    id("application")
}

group = "org.hszg"
version = "1.0-SNAPSHOT"

application {
    mainClass = "org.hszg.Main"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}