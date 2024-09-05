Downloads
---------
Archives containing JAR files are available as [releases](https://github.com/Blizzity/Blizzity/SimpleLogger/releases).

 * Maven (inside the  file)
```xml
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/Blizzity/Blizzity/SimpleLogger</url>
    <snapshots><enabled>true</enabled></snapshots>
  </repository>
  <dependency>
    <groupId>io.github.intisy</groupId>
    <artifactId>simple-logger</artifactId>
    <version>1.12.7</version>
  </dependency>
```

 * Gradle (inside the  or  file)
```groovy
  repositories {
  maven {
        url "https://maven.pkg.github.com/Blizzity/Blizzity/SimpleLogger"
        credentials {
          username = "<your-username>"
          password = "<your-access-token>"
        }
    }
  }
  dependencies {
      implementation 'io.github.intisy:simple-logger:1.12.7'
  }
```
