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




        ibv_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_basket = new Intent(BestSellerActivity.this, BasketActivity.class);
                startActivity(intent_basket);
            }
        });

    }
}
