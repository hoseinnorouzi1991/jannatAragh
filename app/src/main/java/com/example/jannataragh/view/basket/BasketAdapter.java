package com.example.jannataragh.view.basket;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.jannataragh.R;
import com.example.jannataragh.view.product.ProductDetails;

import java.util.ArrayList;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.NewsViewHolder> {

    private MyInterface myInterface;

    public interface MyInterface
    {
        void relativeOnClickListener(String id);
    }

    public  void setRelativeOnClickListener(MyInterface myInterface)
    {
        this.myInterface = myInterface;
    }

    public BasketAdapter(ArrayList<Basket> news){

        this.productsArrayList = news;
    }

    ArrayList<Basket> productsArrayList = new ArrayList<>();

    @NonNull
    @Override
    public BasketAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_basket,viewGroup,false);
        return new BasketAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BasketAdapter.NewsViewHolder productViewHolder, final int i) {


        productViewHolder.onBind(productsArrayList.get(i));


    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
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
        public String id = "";
        public Spinner spinnerNumber;


        public NewsViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgProductBasket = (ImageView)itemView.findViewById(R.id.img_product_basket);
            txtProductTitle = (TextView)itemView.findViewById(R.id.txt_product_title);
            txtTotalPriceValue = (TextView)itemView.findViewById(R.id.txt_total_price_value);
            txtTotalPriceValueDiscount = (TextView)itemView.findViewById(R.id.txt_total_price_value_discount);
            txtFinalPriceValue = (TextView)itemView.findViewById(R.id.txt_final_price_value);
            //txtProductRemoveBasket = (TextView)itemView.findViewById(R.id.txt_product_remove_basket);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
            spinnerNumber = (Spinner)itemView.findViewById(R.id.sp_number_product_basket);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myInterface.relativeOnClickListener(id+"");
                    /*Intent intent = new Intent(itemView.getContext(), ProductDetails.class);
                    intent.putExtra("id", id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(intent);*/
                }
            });

            spinnerNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String str = spinnerNumber.getSelectedItem().toString();
                    Toast.makeText(itemView.getContext(),str + "/ selected",Toast.LENGTH_LONG ).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

        void onBind(Basket model){
            txtProductTitle.setText(model.getTitle());
            txtTotalPriceValue.setText(model.getTotalPrice()+"");
            txtFinalPriceValue.setText(model.getFinalPrice()+"");
            txtTotalPriceValueDiscount.setText(model.getDiscount()+"");
            id = model.getId();

            Glide.with(itemView.getContext()).load("http://www.grafik.computertalk.ir/"+model.getImg())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    }).into(imgProductBasket);

            ArrayAdapter<CharSequence> spinerAdaper = ArrayAdapter.createFromResource(itemView.getContext(),R.array.numbers,android.R.layout.simple_spinner_item);
            spinerAdaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerNumber.setAdapter(spinerAdaper);

            spinnerNumber.setSelection(model.getCount()-1);
        }
    }

}
