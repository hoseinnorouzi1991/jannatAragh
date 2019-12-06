package com.example.jannataragh.date;

import com.example.jannataragh.view.basket.Basket;
import com.example.jannataragh.Product;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IStoreService {

    @GET("/StoreCode/product.php")
    Call<List<Product>> getAllProduct();

    @GET("/StoreCode/productBasket.php")
    Call<List<Basket>> getBasketProduct();

    @GET("/StoreCode/SelectProductById.php")
    Call<List<Basket>> getProductById();
}
