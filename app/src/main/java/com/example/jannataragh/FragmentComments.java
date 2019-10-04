package com.example.jannataragh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentComments extends Fragment {

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

        commentsArrayList = new ArrayList<>();

        commentAdapter = new CommentAdapter(commentsArrayList,getActivity());

        for (int i = 0; i <10 ; i++) {
            Comments comments = new Comments();
            comments.setId(i+1);
            comments.setTitle("عنوان کامنت");
            comments.setDesc("متنی که کاربر در رابطه با نظر خود لحاظ می کند در این قسمت قرار می گیرد.");

            commentsArrayList.add(comments);
        }

        //commentRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));
        commentRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        commentAdapter = new CommentAdapter(commentsArrayList,getActivity());
        commentRecycler.setAdapter(commentAdapter);
        commentRecycler.setRotationY(180);
    }
}