package com.example.myapplication;

public class ItemActivity {
    private int mimageResource;
    private String mText1, mText2;

    public ItemActivity(int mimageResource, String mText1, String mText2) {
        this.mimageResource = mimageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
    }

    public int getMimageResource() {
        return mimageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }
}
