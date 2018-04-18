How to import?
===========
### Gradle
``` gradle
repositories {
    maven {
        url 'http://ananagame.github.io/maven'
    }
}

dependencies {
    compile 'xyz.anana:pine-database:1.2-SNAPSHOT'
}
```

### Maven
``` xml
<repositories>
    <repository>
        <id>anana</id>
        <url>http://ananagame.github.io/maven</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>xyz.anana</groupId>
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

and you can select, update, insert, delete the table object, see more at [the Table class](https://github.com/AnanaGame/pine-database/blob/master/src/main/java/xyz/anana/database/objects/Table.java)

[French video tutorial on this library](https://youtu.be/qcwDNYUXkhw)
