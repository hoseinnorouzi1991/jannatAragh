package com.example.jannataragh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentProperties extends Fragment implements ProductDetails.DataReceivedListener{

    String title,desc,price;
    RelativeLayout relativeProperties;
    TextView txtPropertyProductTitle;
    TextView txtPropertiesExplain;
    TextView txtPropertiesPrice;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_properties,container,false);
//        Bundle bundle = new Bundle();
//        bundle = getArguments();
//
//        if (bundle != null) {
//            String name = bundle.getString("name");
//            Toast.makeText(MainActivity.context, name, Toast.LENGTH_SHORT).show();
//        }

        return layout;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProductDetails mActivity = (ProductDetails) getActivity();
        mActivity.setAboutDataListener(FragmentProperties.this);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        relativeProperties = (RelativeLayout)view.findViewById(R.id.relative_properties);

        relativeProperties.setRotationY(180);

        txtPropertyProductTitle = (TextView)view.findViewById(R.id.txt_properties_product_title);
        txtPropertiesExplain = (TextView)view.findViewById(R.id.txt_properties_explain);
        txtPropertiesPrice = (TextView)view.findViewById(R.id.txt_properties_price);

    }

    @Override
    public void onDataReceived(JSONObject jsonObject) {
        try {
            txtPropertyProductTitle.setText(jsonObject.getString("name"));
            txtPropertiesExplain.setText(jsonObject.getString("desc"));
            txtPropertiesPrice.setText(jsonObject.getString("price"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
