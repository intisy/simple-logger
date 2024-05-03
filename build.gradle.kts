plugins {
    id("java")
    id("maven-publish")
}

group = "com.github.WildePizza"

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