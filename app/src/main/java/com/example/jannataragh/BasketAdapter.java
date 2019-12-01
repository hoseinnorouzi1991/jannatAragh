package com.example.jannataragh;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jannataragh.view.basket.Basket;
import com.example.jannataragh.view.basket.BasketActivity;
import com.example.jannataragh.view.product.ProductDetails;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.NewsViewHolder> {

    ArrayList<Basket> newsArrayList = new ArrayList<>();
    Context context;


    public BasketAdapter(ArrayList<Basket> news, Context context){

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
    public void onBindViewHolder(@NonNull final BasketAdapter.NewsViewHolder productViewHolder, final int i) {

        final Basket basket = newsArrayList.get(i);
        productViewHolder.txtProductTitle.setText(basket.getTitle());
        productViewHolder.txtTotalPriceValue.setText(basket.getTotalPrice());
        productViewHolder.txtTotalPriceValueDiscount.setText(basket.getDiscount());
        productViewHolder.txtFinalPriceValue.setText(basket.getFinalPrice());

        ArrayAdapter<CharSequence> spinerAdaper = ArrayAdapter.createFromResource(MainActivity.context,R.array.numbers,android.R.layout.simple_spinner_item);
        spinerAdaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productViewHolder.spinnerNumber.setAdapter(spinerAdaper);

        productViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.context, ProductDetails.class);
                intent.putExtra("id", basket.getId());
                context.startActivity(intent);
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
        public Spinner spinnerNumber;



        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProductBasket = (ImageView)itemView.findViewById(R.id.img_product_basket);
            txtProductTitle = (TextView)itemView.findViewById(R.id.txt_product_title);
            txtTotalPriceValue = (TextView)itemView.findViewById(R.id.txt_total_price_value);
            txtTotalPriceValueDiscount = (TextView)itemView.findViewById(R.id.txt_total_price_value_discount);
            txtFinalPriceValue = (TextView)itemView.findViewById(R.id.txt_final_price_value);
            //txtProductRemoveBasket = (TextView)itemView.findViewById(R.id.txt_product_remove_basket);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
            spinnerNumber = (Spinner)itemView.findViewById(R.id.sp_number_product_basket);
        }
    }

}
