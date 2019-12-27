package com.example.jannataragh.view.basket;

public class Basket {

    private String id;
    private int user_id;
    private String product_id;
    private String title;
    private int price;
    private int totalPrice;
    private int finalPrice;
    private String img;
    private int count;
    private int discount;

    public Basket(String id, int user_id,String product_id, String title, int price, int totalPrice, int finalPrice, String img, int count,int discount) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.title = title;
        this.price = price;
        this.totalPrice = totalPrice;
        this.finalPrice = finalPrice;
        this.img = img;
        this.count = count;
        this.discount = discount;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalPrice() {
        if(count != 0)
        {
            totalPrice = count * price;
        }

        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getFinalPrice() {

        if (discount != 0)
        {
            discount = price * (discount/100);
            finalPrice = price - discount;
            return finalPrice;
        }
        else
        {
            return totalPrice;
        }
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
