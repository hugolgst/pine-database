# PineDatabase
PineDatabase is an intelligent MySQL database library.

# How to use?
Firstly you need to get the Table object:
``` java
	Database database = new Database("The database host adress", "The database name", "The user", "The password");
        Table table = database.getTable("The table name");
```

and you can select, update, insert, delete the table object, see more at [the Table class](https://github.com/AnanaGame/pine-database/blob/master/src/main/java/xyz/anana/database/objects/Table.java)
