package com.example.jannataragh.view.base;

import android.support.v4.app.Fragment;

import org.json.JSONObject;

public abstract class BaseFragment extends Fragment {

    public abstract void showData(JSONObject jsonObject);
}
