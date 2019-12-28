package com.example.jannataragh.date;

import com.example.jannataragh.view.basket.Basket;
import com.example.jannataragh.view.main.Product;
import com.example.jannataragh.view.user.User;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IStoreService {

    @GET("/StoreCode/product.php")
    Call<List<Product>> getAllProduct();

    @GET("/StoreCode/productBasket.php")
    Call<List<Basket>> getBasketProduct();

    @GET("/StoreCode/SelectProductById.php")
    Call<List<Basket>> getProductById();

    @GET("/StoreCode/getLoginedUser")
    Call<User> getLoginedInUser();

    @GET("/StoreCode/basketItemCount.php")
    Call<JsonObject> getBasketCount(@Query("user_id") String user_id);

}
