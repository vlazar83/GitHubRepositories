package com.example.i060663.githubrepositories;

public class Repository {

    private String name;
    private String fullName;

    public Repository(String name, String fullName){

        this.name = name;
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
