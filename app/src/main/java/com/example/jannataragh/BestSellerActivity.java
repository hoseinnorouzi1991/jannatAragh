package com.example.jannataragh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import ru.nikartm.support.ImageBadgeView;

public class BestSellerActivity extends AppCompatActivity {

    RecyclerView recyclerBestSeller;
    RecyclerAdapter recyclerAdapter;
    ArrayList<News> newsArrayList;

    ImageBadgeView ibv_basket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_seller);

        ibv_basket = (ImageBadgeView)findViewById(R.id.ibv_basket);

        recyclerBestSeller = (RecyclerView)findViewById(R.id.recycler_best_seller);
        recyclerBestSeller.setHasFixedSize(true);

        newsArrayList = new ArrayList<>();

        for (int i=0; i<10;i++)
        {
            News news = new News();
            news.setId(i+1);
            news.setTitle("عرق خارخاسک");
            news.setDesc("این عرق برای انواع بیماری های و درمان آن ها استفاده می شود. این عرق به صورت کاملا طبیعی و بدون هیچ ناخالصی تولید می شود.");
            news.setPrice("12,000");
            news.setToman("تومان");

            newsArrayList.add(news);
        }


        recyclerBestSeller.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        recyclerAdapter = new RecyclerAdapter(newsArrayList,BestSellerActivity.this);
        recyclerBestSeller.setAdapter(recyclerAdapter);


        ibv_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_basket = new Intent(BestSellerActivity.this, BasketActivity.class);
                startActivity(intent_basket);
            }
        });

    }
}
