package com.example.jannataragh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jannataragh.view.basket.BasketActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.nikartm.support.ImageBadgeView;

public class BestPropertyActivity extends AppCompatActivity {

    RecyclerView recyclerBestProperty;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Product> productArrayList;

    ImageBadgeView ibv_basket;
    ProgressBar progressBarBestProperties;

    String url = "http://www.grafik.computertalk.ir/StoreCode/product.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_property);

        ibv_basket = (ImageBadgeView)findViewById(R.id.ibv_basket);
        progressBarBestProperties = findViewById(R.id.progressbar_best_properties);

        recyclerBestProperty = (RecyclerView)findViewById(R.id.recycler_best_property);
        recyclerBestProperty.setHasFixedSize(true);

        productArrayList = new ArrayList<>();

        recyclerBestProperty.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
        showData();


//        for (int i=0; i<10;i++)
//        {
//            Product news = new Product();
//            news.setId(i+1);
//            news.setTitle("عرق خارخاسک");
//            news.setDesc("این عرق برای انواع بیماری های و درمان آن ها استفاده می شود. این عرق به صورت کاملا طبیعی و بدون هیچ ناخالصی تولید می شود.");
//            news.setPrice("12,000");
//            news.setToman("تومان");
//
//            newsArrayList.add(news);
//        }


//        recyclerBestProperty.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
//        recyclerAdapter = new RecyclerAdapter(newsArrayList,BestPropertyActivity.this);
//        recyclerBestProperty.setAdapter(recyclerAdapter);


        ibv_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_basket = new Intent(BestPropertyActivity.this, BasketActivity.class);
                startActivity(intent_basket);
            }
        });

    }

    private void showData() {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        productArrayList.add(new Product(jsonObject.getString("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("desc"),
                                jsonObject.getString("price"),
                                jsonObject.getString("img")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                progressBarBestProperties.setVisibility(View.GONE);

                recyclerAdapter = new RecyclerAdapter(productArrayList, MainActivity.context);
                recyclerBestProperty.setAdapter(recyclerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BestPropertyActivity.this, "مشکلی رخ داده است.اتصال اینترنت خود را بررسی کنید.", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
                progressBarBestProperties.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue1 = Volley.newRequestQueue(MainActivity.context);
        requestQueue1.add(request);

//        Animation animation = null;
//        animation = AnimationUtils.loadAnimation(MainActivity.context, R.anim.listview);
//        animation.setDuration(200);
//        listView.startAnimation(animation);
//        animation = null;
    }
}
