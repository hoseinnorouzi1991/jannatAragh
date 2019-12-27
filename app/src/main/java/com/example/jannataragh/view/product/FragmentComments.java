package com.example.jannataragh.view.product;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jannataragh.view.comment.CommentAdapter;
import com.example.jannataragh.view.comment.Comments;
import com.example.jannataragh.R;
import com.example.jannataragh.view.base.BaseCommentFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentComments extends BaseCommentFragment {

    RecyclerView commentRecycler;
    CommentAdapter commentAdapter;
    ArrayList<Comments> commentsArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_comments,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        commentRecycler = (RecyclerView)view.findViewById(R.id.comment_recycler);

        /*for (int i = 0; i <10 ; i++) {
            Comments comments = new Comments();
            comments.setId(i+1);
            comments.setTitle("عنوان کامنت");
            comments.setDesc("متنی که کاربر در رابطه با نظر خود لحاظ می کند در این قسمت قرار می گیرد.");
            comments.setLike("10");
            comments.setDislike("5");

            commentsArrayList.add(comments);
        }*/

        //commentRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));


    }


    @Override
    public void recieveData(JSONArray jsonArray) {

        commentsArrayList = new ArrayList<>();
        commentAdapter = new CommentAdapter(commentsArrayList,getActivity());

        for (int i=0; i<jsonArray.length(); i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null) {
                    commentsArrayList.add(new Comments(jsonObject.getString("id"),
                            jsonObject.getString("full_name"),
                            jsonObject.getString("comment_content"),
                            jsonObject.getString("like_comment"),
                            jsonObject.getString("dislike_comment")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        commentRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        commentAdapter = new CommentAdapter(commentsArrayList,getActivity());
        commentRecycler.setAdapter(commentAdapter);

        commentRecycler.setRotationY(180);
    }
}
