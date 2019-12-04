package com.example.jannataragh.view.basket;

public class Basket {

    private int id;
    private int userId;
    private String title;
    private String totalPrice;
    private String finalPrice;
    private String image;
    private String count;

    public Basket(int id,int userId, String title, String totalPrice, String finalPrice, String image, String count) {
        this.id = id;
        this.userId  =userId;
        this.title = title;
        this.totalPrice = totalPrice;
        this.finalPrice = finalPrice;
        this.image = image;
        this.count = count;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }



    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
