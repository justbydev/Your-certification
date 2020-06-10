package com.example.coolpiece.home.search;

public class Search {
    String name;
    int type;

    public Search(String name, int type){
        this.name=name;
        this.type=type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
