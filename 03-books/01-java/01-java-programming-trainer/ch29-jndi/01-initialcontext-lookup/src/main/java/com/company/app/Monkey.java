package com.company.app;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

public class Monkey implements Referenceable {
    private String name;
    private String favoriteFruit;
    private boolean likesBananas;

    public Monkey(){}

    public Monkey(String name, String favoriteFruit, boolean likesBananas){
        this.name = name;
        this.favoriteFruit = favoriteFruit;
        this.likesBananas = likesBananas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    public boolean isLikesBananas() {
        return likesBananas;
    }

    public void setLikesBananas(boolean likesBananas) {
        this.likesBananas = likesBananas;
    }

    @Override
    public Reference getReference() throws NamingException {
        Reference reference = new Reference(Monkey.class.getName());
        reference.add(new StringRefAddr("name", this.name));
        reference.add(new StringRefAddr("favoriteFruit", this.favoriteFruit));
        reference.add(new StringRefAddr("likesBananas", Boolean.toString(this.likesBananas)));
        return reference;
    }
}
