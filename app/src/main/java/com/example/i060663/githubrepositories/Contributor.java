package com.example.i060663.githubrepositories;

import android.graphics.Bitmap;

public class Contributor {

    private String loginName;
    private String avatarURL;
    private Bitmap avatarPicture;

    public Bitmap getAvatarPicture() {
        return avatarPicture;
    }

    public void setAvatarPicture(Bitmap avatarPicture) {
        this.avatarPicture = avatarPicture;
    }

    public Contributor(String loginName, String avatarURL, Bitmap avatarPicture) {
        this.loginName = loginName;
        this.avatarURL = avatarURL;
        this.avatarPicture = avatarPicture;
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
