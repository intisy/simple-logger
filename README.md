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
    <groupId>com.github.WildePizza</groupId>
    <artifactId>simple-logger</artifactId>
    <version>1.12</version>
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
      implementation 'com.github.WildePizza:simple-logger:1.12'
  }
```
