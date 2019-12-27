package com.example.jannataragh.view.main;

import com.android.volley.toolbox.StringRequest;

public class Product {

    private String id;
    private String title;
    private String desc;
    private String price;
    private String img;

    public Product(String id, String title, String desc, String price, String img) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.img = img;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
