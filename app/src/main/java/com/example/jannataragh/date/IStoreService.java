package com.example.jannataragh.date;

import com.example.jannataragh.view.basket.Basket;
import com.example.jannataragh.view.main.Product;
import com.example.jannataragh.view.user.User;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("/StoreCode/basketItemCount.php")
    Call<JsonObject> isFave(@Query("user_id") String user_id, @Query("product_id") String product_id);

    @GET("/StoreCode/favoriteLoad.php")
    Call<JsonObject> loadFave(@Query("user_id") String user_id, @Query("product_id") String product_id);

    @POST("/StoreCode/favoriteUpdate.php")
    Call<JsonObject> updateFave(@Query("user_id") String user_id, @Query("product_id") String product_id);
}
