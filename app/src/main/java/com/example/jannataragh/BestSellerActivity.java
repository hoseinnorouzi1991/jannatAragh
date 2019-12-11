package com.example.jannataragh;

import android.content.Intent;
import android.os.AsyncTask;
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

public class BestSellerActivity extends AppCompatActivity {

    RecyclerView recyclerBestSeller;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Product> productArrayList;

    ImageBadgeView ibv_basket;
    ProgressBar progressBarBestSeller;

    String url = "http://www.grafik.computertalk.ir/StoreCode/product.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);

        ibv_basket = (ImageBadgeView)findViewById(R.id.ibv_basket);
        progressBarBestSeller = findViewById(R.id.progressbar_best_seller);

        recyclerBestSeller = (RecyclerView)findViewById(R.id.recycler_best_seller);
        recyclerBestSeller.setHasFixedSize(true);

        productArrayList = new ArrayList<>();

        //recyclerBestSeller.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerBestSeller.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
        showData();

        ibv_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_basket = new Intent(BestSellerActivity.this, BasketActivity.class);
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

                progressBarBestSeller.setVisibility(View.GONE);
                recyclerAdapter = new RecyclerAdapter(productArrayList, MainActivity.context);
                recyclerBestSeller.setAdapter(recyclerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BestSellerActivity.this, "مشکلی رخ داده است.اتصال اینترنت خود را بررسی کنید.", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
                progressBarBestSeller.setVisibility(View.GONE);
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


    private class Task extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            showData();
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Call setRefreshing(false) when the list has been refreshed.
            //mRefreshLayout.finishRefreshing();
            //runLayoutAnimation(recycler);
            super.onPostExecute(result);
        }
    }
}
