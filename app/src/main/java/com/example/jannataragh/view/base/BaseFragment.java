package com.example.jannataragh.view.base;

import android.support.v4.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BaseFragment extends Fragment {

    public abstract void showData(JSONArray jsonArray);
}
