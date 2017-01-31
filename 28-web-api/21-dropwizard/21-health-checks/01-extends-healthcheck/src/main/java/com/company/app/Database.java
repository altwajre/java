package com.company.app;

public class Database {

    public Database(boolean isConnected){
        this.isConnected = isConnected;
    }

    private boolean isConnected;

    public boolean isConnected() {
        return isConnected;
    }
}
