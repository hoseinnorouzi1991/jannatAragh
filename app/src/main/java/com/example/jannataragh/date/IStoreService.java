package com.example.jannataragh.date;

import com.example.jannataragh.Basket;
import com.example.jannataragh.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IStoreService {

    @GET("/product")
    Call<List<Product>> getAllProduct();

    @GET("/productBasket")
    Call<List<Basket>> getBasketProduct();
}
