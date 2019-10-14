package com.example.jannataragh;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.NewsViewHolder> {

    ArrayList<basket> newsArrayList = new ArrayList<>();
    Context context;

    public BasketAdapter(ArrayList<basket> news, Context context){

        this.newsArrayList = news;
        this.context = context;
    }

    @NonNull
    @Override
    public BasketAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_basket,viewGroup,false);
        return new BasketAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BasketAdapter.NewsViewHolder newsViewHolder, final int i) {

        basket basket = newsArrayList.get(i);
        newsViewHolder.txtProductTitle.setText(basket.getTitle());
        newsViewHolder.txtTotalPriceValue.setText(basket.getTotalPrice());
        newsViewHolder.txtTotalPriceValueDiscount.setText(basket.getDiscount());
        newsViewHolder.txtFinalPriceValue.setText(basket.getFinalPrice());

        newsViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.context,ProductDetails.class);
                MainActivity.context.startActivity(intent);
                Toast.makeText(context,i+"",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgProductBasket;
        public TextView txtProductTitle;
        public TextView txtTotalPriceValue;
        public TextView txtTotalPriceValueDiscount;
        public TextView txtFinalPriceValue;
        //public TextView txtProductRemoveBasket;
        public RelativeLayout relativeLayout;



        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProductBasket = (ImageView)itemView.findViewById(R.id.img_product_basket);
            txtProductTitle = (TextView)itemView.findViewById(R.id.txt_product_title);
            txtTotalPriceValue = (TextView)itemView.findViewById(R.id.txt_total_price_value);
            txtTotalPriceValueDiscount = (TextView)itemView.findViewById(R.id.txt_total_price_value_discount);
            txtFinalPriceValue = (TextView)itemView.findViewById(R.id.txt_final_price_value);
            //txtProductRemoveBasket = (TextView)itemView.findViewById(R.id.txt_product_remove_basket);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }

}
