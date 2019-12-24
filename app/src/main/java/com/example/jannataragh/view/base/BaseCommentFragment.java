package com.example.jannataragh.view.base;

import android.support.v4.app.Fragment;

import org.json.JSONArray;

public abstract class BaseCommentFragment extends Fragment {

    public abstract void recieveData(JSONArray jsonArray);

}
