package com.example.i060663.githubrepositories;

public class Contributor {

    private String loginName;
    private String avatarURL;

    public Contributor(String loginName, String avatarURL) {
        this.loginName = loginName;
        this.avatarURL = avatarURL;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
