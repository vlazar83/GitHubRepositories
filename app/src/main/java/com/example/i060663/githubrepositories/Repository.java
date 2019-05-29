package com.example.i060663.githubrepositories;

import java.io.Serializable;

public class Repository implements Serializable {

    private String name;
    private String fullName;
    private int size;
    private int stargazersCount;
    private int forksCount;
    private String contributorsURL;

    public String getContributorsURL() {
        return contributorsURL;
    }

    public void setContributorsURL(String contributorsURL) {
        this.contributorsURL = contributorsURL;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Repository(String name, String fullName, int size, int stargazersCount, int forksCount, String contributorsURL){

        this.name = name;
        this.fullName = fullName;
        this.size = size;
        this.stargazersCount = stargazersCount;
        this.forksCount = forksCount;
        this.contributorsURL = contributorsURL;
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
