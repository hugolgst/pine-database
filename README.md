How to import?
===========
### Gradle
``` gradle
repositories {
    maven {
        url 'http://hugolgst.github.io/maven'
    }
}

dependencies {
    compile 'xyz.hugol:pine-database:1.2-SNAPSHOT'
}
```

### Maven
``` xml
<repositories>
    <repository>
        <id>hugol</id>
        <url>http://hugolgst.github.io/maven</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>xyz.hugol</groupId>
        <artifactId>pine-database</artifactId>
        <version>1.2-SNAPSHOT</version>
    </dependency>
</dependencies>
```

Or simply download the release.

# How to use pine-database ?
Firstly you need to get the Table object:
``` java
Database database = new Database("The database host adress", "The database name", "The user", "The password");
Table table = database.getTable("The table name");
```

hugolgst

[French video tutorial on this library](https://youtu.be/qcwDNYUXkhw)
