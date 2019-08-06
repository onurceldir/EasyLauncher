package com.example.mylauncher;

import android.graphics.drawable.Drawable;

class AppList {
    private String name;
    Drawable icon;
    private String package_path;

    public AppList(String name, Drawable icon, String package_path) {
        this.name = name;
        this.icon = icon;
        this.package_path = package_path;
    }

    public String getName() {
        return name;
    }
    public Drawable getIcon() {
        return icon;
    }
    public String getPackage(){ return package_path;}

}

