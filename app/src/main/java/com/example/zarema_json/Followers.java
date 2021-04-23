package com.example.zarema_json;

import android.graphics.Bitmap;

public class Followers {
    private int id;
    private String name;
    private String userURL;
    private Bitmap icon;
    private String iconName;
    private String repository;



    public void setId(int id){this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setUserURL(String userURL) {this.userURL = userURL;}
    public void setIcon(Bitmap icon) {this.icon = icon;}
    public void setIconName(String iconName) {this.iconName = iconName;}
    public void setRepository(String repository) {this.repository = repository;}


    public int getId() {return id;}
    public String getName() {return name;}
    public String getUserURL() {return userURL;}
    public Bitmap getIcon() {return icon;}
    public String getIconName() {return iconName;}
    public String getRepository() {return repository;}


}
