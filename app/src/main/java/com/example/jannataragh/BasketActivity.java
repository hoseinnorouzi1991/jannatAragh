package com.example.jannataragh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {

    RecyclerView recyclerView_basket;
    ArrayList<basket> arrayList_basket;
    BasketAdapter arrayAdapter_basket;

    RelativeLayout relativeFinalBuy;

    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recyclerView_basket = (RecyclerView)findViewById(R.id.recycler_basket);
        arrayList_basket = new ArrayList<>();
        relativeFinalBuy = (RelativeLayout)findViewById(R.id.relative_final_buy);
//        spinner = (Spinner)findViewById(R.id.sp_number_product_basket);

//        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
//        spinner.setAdapter(adapter);

        for (int i=0; i<10;i++)
        {
            basket basket = new basket();
            basket.setId(i+1);
            basket.setTitle("نام محصول");
            basket.setTotalPrice("8,000");
            basket.setDiscount("2,000");
            basket.setFinalPrice("6,000");

            arrayList_basket.add(basket);

        }

        recyclerView_basket.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        arrayAdapter_basket = new BasketAdapter(arrayList_basket,this);

        recyclerView_basket.setAdapter(arrayAdapter_basket);


        relativeFinalBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BasketActivity.this,"clicked",Toast.LENGTH_LONG).show();
            }
        });

    }
}
