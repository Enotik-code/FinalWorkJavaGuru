package by.edabudet.config;

import lombok.Getter;

@Getter
class DatabaseConfig {
    private DatabaseConfig() {
        throw new IllegalStateException("Utility class");
    }

    protected static final String DB_HOST = "127.0.0.1";
    protected static final String DB_PORT = "3306";
    protected static final String DB_USER = "root";
    protected static final String DB_PASS = "1234";
    protected static final String DB_NAME = "product";

}

