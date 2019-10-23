package com.example.jannataragh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentsViewHolder>{

    ArrayList<Comments> commentArrayList = new ArrayList<>();
    Context context;

    public CommentAdapter(ArrayList<Comments> comments, Context context){

        this.commentArrayList = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_comments,viewGroup,false);
        return new CommentAdapter.CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentAdapter.CommentsViewHolder CommentsViewHolder, final int i) {

        Comments model = commentArrayList.get(i);
        CommentsViewHolder.txtTitle.setText(model.getTitle());
        CommentsViewHolder.txtDesc.setText(model.getDesc());
        CommentsViewHolder.txtLike.setText(model.getLike());
        CommentsViewHolder.txtDislike.setText(model.getDislike());


//        newsViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent intent = new Intent(MainActivity.context,ProductDetails.class);
//                //MainActivity.context.startActivity(intent);
//                Toast.makeText(context,i+"",Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }


    public class CommentsViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtTitle;
        public TextView txtDesc;
        public TextView txtLike;
        public TextView txtDislike;
        //public RelativeLayout relativeLayout;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = (TextView)itemView.findViewById(R.id.txt_recycler_titleComments);
            txtDesc = (TextView)itemView.findViewById(R.id.txt_recycler_descComments);
            txtLike = (TextView)itemView.findViewById(R.id.txt_like_counter);
            txtDislike = (TextView)itemView.findViewById(R.id.txt_dislike_counter);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative);
        }
    }
}
