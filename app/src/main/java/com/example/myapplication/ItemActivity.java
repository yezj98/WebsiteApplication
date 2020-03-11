package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class ItemActivity {
    private String mimageResource;
    private String mText1, mText2;


    public ItemActivity(String mimageResource, String mText1, String mText2) {
        this.mimageResource = mimageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
    }

    public void changetext (String s) {
        mimageResource = s;
    }

    public String getMimageResource() {
        return mimageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }
}
