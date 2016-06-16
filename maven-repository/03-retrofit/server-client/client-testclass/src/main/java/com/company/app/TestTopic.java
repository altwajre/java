package com.company.app;

/*
test class for server side Topic:
 - the class name can be different
 - the fields should be the same
 */
public class TestTopic {
    private int id;
    private String name;
    public TestTopic(){
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
        return String.format("TestTopic{id=%s, name=%s}", id, name);
    }
}
