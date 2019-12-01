package com.example.jannataragh.view.basket;

public class Basket {

    private int id;
    private String title;
    private String totalPrice;
    private  String discount;
    private  String finalPrice;


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

    public String getDiscount() {
        return discount;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }
}
