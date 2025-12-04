package SolidPrinciples;

import javax.xml.crypto.Data;

// DIP -> Dependency Inversion Principle
// High level modules should not depend on low level modules, both should depend on abstractions
public class D {
    private final MongoDB mongoDB;

    public D(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

    void saveData() {
        // what if we migrated to a different DB
        // this is the reason it should depend on abstraction, not on a specific concrete implementation
        mongoDB.connect();
    }
}

class MongoDB {
    void connect() {
        System.out.println("Connect to database");
    }

    ;
}

// Good Practice

interface Database {
    void connect();
}

class PostgreSqlDatabase implements Database {

    @Override
    public void connect() {

    }
}

class MySqlDatabase implements Database {

    @Override
    public void connect() {

    }
}

class DatabaseService{

    // even if we change the database this will still work, as it depends on abstraction
    private final Database database;

    DatabaseService(Database database) {
        this.database = database;
    }

    void saveData(){
        database.connect();
    }
}