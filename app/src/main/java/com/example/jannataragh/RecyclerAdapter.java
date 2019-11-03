package com.example.jannataragh;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
    public void onBindViewHolder(@NonNull final NewsViewHolder newsViewHolder, final int i) {

        final News model = newsArrayList.get(i);
        newsViewHolder.txtTitle.setText(model.getTitle());
        newsViewHolder.txtDesc.setText(model.getDesc());
        newsViewHolder.txtPrice.setText(model.getPrice());

        Glide.with(MainActivity.context).load("http://www.grafik.computertalk.ir/"+newsArrayList.get(i).getImg())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        //progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(newsViewHolder.imgPicture);

        newsViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.context,ProductDetails.class);
                intent.putExtra("id",model.getId()+"");
                context.startActivity(intent);
                //Toast.makeText(context,i+"",Toast.LENGTH_SHORT).show();
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
        public ImageView imgPicture;
        public RelativeLayout relativeLayout;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPicture = (ImageView)itemView.findViewById(R.id.img_recycler_news);
            txtTitle = (TextView)itemView.findViewById(R.id.txt_recycler_titleNews);
            txtDesc = (TextView)itemView.findViewById(R.id.txt_recycler_descNews);
            txtPrice = (TextView)itemView.findViewById(R.id.txt_recycler_price);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }
}
