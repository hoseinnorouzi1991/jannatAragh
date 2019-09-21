package com.example.jannataragh;

import android.content.Context;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NewsViewHolder> {

    ArrayList<News> newsArrayList = new ArrayList<>();
    Context context;

    public RecyclerAdapter(ArrayList<News> news, Context context){

        this.newsArrayList = news;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_product_horizontal,viewGroup,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, final int i) {

        News model = newsArrayList.get(i);
        newsViewHolder.txtTitle.setText(model.getTitle());
        newsViewHolder.txtDesc.setText(model.getDesc());
        newsViewHolder.txtPrice.setText(model.getPrice());
        newsViewHolder.txtToman.setText(model.getToman());

        newsViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        public ImageView imgNews;
        public TextView txtTitle;
        public TextView txtDesc;
        public TextView txtPrice;
        public TextView txtToman;
        public RelativeLayout relativeLayout;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = (ImageView)itemView.findViewById(R.id.img_recycler_news);
            txtTitle = (TextView)itemView.findViewById(R.id.txt_recycler_titleNews);
            txtDesc = (TextView)itemView.findViewById(R.id.txt_recycler_descNews);
            txtPrice = (TextView)itemView.findViewById(R.id.txt_recycler_price);
            txtToman = (TextView)itemView.findViewById(R.id.txt_recycler_toman);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }
}
