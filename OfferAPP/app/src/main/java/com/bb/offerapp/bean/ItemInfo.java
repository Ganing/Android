package com.bb.offerapp.bean;


/**
 * Created by bb on 2016/7/28.
 */
public class ItemInfo {

    private int portrait = android.R.mipmap.sym_def_app_icon;

    private String nickName;

    private String motto;


    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

}
