plugins {
    id("java")
    id("maven-publish")
}

group = "wildepizza.com.github.simplelogger"

repositories {
    mavenCentral()
}
publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            groupId = group.toString()
            artifactId = "simple-logger" // Use a lowercase artifactId (convention)
        }
    }
}

tasks.jar {
    archiveFileName.set("SimpleLogger.jar") // Set the name of the JAR file here
}