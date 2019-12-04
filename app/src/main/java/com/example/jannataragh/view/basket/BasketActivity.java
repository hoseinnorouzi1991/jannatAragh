package com.example.jannataragh.view.basket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jannataragh.BasketAdapter;
import com.example.jannataragh.R;
import com.example.jannataragh.date.IStoreService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasketActivity extends AppCompatActivity {

    RecyclerView recyclerView_basket;
    ArrayList<Basket> arrayList_basket;
    BasketAdapter arrayAdapter_basket;

    RelativeLayout relativeFinalBuy;

    Spinner spinner;

    String baseUrl = "http://grafik.computertalk.ir/";
    IStoreService mIStoreService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recyclerView_basket = (RecyclerView) findViewById(R.id.recycler_basket);
        arrayList_basket = new ArrayList<>();
        relativeFinalBuy = (RelativeLayout) findViewById(R.id.relative_final_buy);


/*        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);*/

        /*for (int i=0; i<10;i++)
        {
            Basket basket = new Basket();
            basket.setId(i+1);
            basket.setTitle("نام محصول");
            basket.setTotalPrice("8,000");
            basket.setDiscount("2,000");
            basket.setFinalPrice("6,000");

            arrayList_basket.add(basket);

        }*/


        RetrofitExecute();

        mIStoreService.getBasketProduct().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(String.valueOf(response));
                        for (int i = 0; i < jsonObject.length(); i++) {

                            int count = Integer.parseInt(jsonObject.getString("count"));
                            int price = Integer.parseInt(jsonObject.getString("price"));
                            int totalPrice = price;
                            int discount = Integer.parseInt(jsonObject.getString("discount"));
                            int finalPrice = price;

                            if(count != 0)
                            {
                                totalPrice = count * price;
                            }
                            if (discount != 0)
                            {
                                discount = price * (discount/100);
                                finalPrice = price - discount;
                            }

                            arrayList_basket.add(new Basket(jsonObject.getInt("id"),
                                    jsonObject.getInt("user_id"),
                                    jsonObject.getString("name"),
                                    totalPrice+"",
                                    finalPrice+"",
                                    jsonObject.getString("img"),
                                    jsonObject.getString("count")));
                            int j=0;

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(BasketActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(BasketActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        recyclerView_basket.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        arrayAdapter_basket = new BasketAdapter(arrayList_basket, this);

        recyclerView_basket.setAdapter(arrayAdapter_basket);


        relativeFinalBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BasketActivity.this, "clicked", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void RetrofitExecute() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mIStoreService = retrofit.create(IStoreService.class);

    }
}
