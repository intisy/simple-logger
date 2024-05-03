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
            artifactId = "SimpleLogger"
        }
    }
}

tasks.jar {
    archiveFileName.set("SimpleLogger.jar")
}