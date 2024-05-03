plugins {
    id("java")
    id("maven-publish")
}

group = "wildepizza.com.github.logger"
version = "1.2"

repositories {
    mavenCentral()
}

tasks.jar {
    archiveFileName.set("SimpleLogger.jar") // Set the name of the JAR file here
}