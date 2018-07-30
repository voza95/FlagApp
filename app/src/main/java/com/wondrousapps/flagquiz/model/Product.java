package com.wondrousapps.flagquiz.model;

/**
 * Created by ntf-42 on 5/3/18.
 */

public class Product{
    int image;
    String title,info;

    public Product(int image, String title, String info) {
        this.image = image;
        this.title = title;
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
