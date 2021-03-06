package com.company.app.model;

public class Topic {
    private int id;
    private String name;
    public Topic(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("Topic{id=%s, name=%s}", id, name);
    }
}
